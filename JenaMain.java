import java.io.File;
import java.lang.String;
import java.lang.Object;
import com.hp.hpl.jena.util.FileUtils;

import java.io.*;
 import java.net.*;

import java.util.*;
import java.util.Collections;
import java.net.URI;
import java.net.URISyntaxException;


import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.jena.*;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.ProtegeOWL;

public class JenaMain {
  public static void main(String[] args) {
    try {

      JenaOWLModel jModel;

      Collection errors = new ArrayList();
      jModel = ProtegeOWL.createJenaOWLModel();

	//Establecemos la URI principal de nuestra ontologia
	jModel.getDefaultOWLOntology()
              .rename("http://www.owl-ontologies.com/OntoAnimales.owl");
	//Establecemos el namespace por defecto
	jModel.getNamespaceManager()
              .setDefaultNamespace("http://www.owl-ontologies.com/OntoAnimales.owl#");

	//Obtenemos el namespace por defecto que acabamos de configurar
	String NS = jModel.getNamespaceManager().getDefaultNamespace();

	//Creamos la clase animal
	OWLNamedClass clsAnimal = jModel.createOWLNamedClass("Animales");
	//Vamos a crear las subclases Vertebrados e Invertebrados
	OWLNamedClass clsVert;
        clsVert = jModel.createOWLNamedSubclass("Vertebrados", clsAnimal);
        OWLNamedClass clsInVert;
        clsInVert = jModel.createOWLNamedSubclass("Invertebrados", clsAnimal);

	//Decimos que un vertebrado no puede ser invertebrado y viceversa
	//clsAnimal.setSubclassesDisjoint(true);

	//Vamos a crear algunas propiedades, como el peso,... o las que queramos
	OWLDatatypeProperty propPeso = jModel.createOWLDatatypeProperty("peso");
	propPeso.setFunctional(true);
	//Definimos la propiedad como Functional para que sólo pueda contener un
        //valor. En OWL cada propiedad puede contener una lista de valores, no un
        //único valor. Pero en este caso no nos interesa eso.

	//Asignamos la propiedad a la clase Animal
	propPeso.setDomain(clsAnimal);
	//Definimos el tipo (rango) de la propiedad como float
	propPeso.setRange(((OWLModel)jModel).getXSDfloat());

	//Vamos a crear un par de instancias
	OWLIndividual leopard = clsVert.createOWLIndividual("Leopardo");
	//Le asignamos un peso al leopardo
	leopard.setPropertyValue(propPeso, new Float(250.49f));

	//Creamos un pulpo
	OWLIndividual pulpo = clsInVert.createOWLIndividual("Pulpo");
	//Le asignamos un peso
	pulpo.setPropertyValue(propPeso, new Float(14.20f));

	//La Guardamos en un fichero owl
	jModel.save(new URI("file:/home/blanca/cenidpd/talkprotege/Animales.owl"),
                    FileUtils.langXMLAbbrev,errors);
	System.out.println("ERRORES: "+errors);

	} catch (OntologyLoadException oe){
		oe.printStackTrace();
	} catch (URISyntaxException e) {
		e.printStackTrace();
	}
   }
}
