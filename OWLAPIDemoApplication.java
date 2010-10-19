/*package com.demo.application;*/

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.jena.*;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.*;


public class OWLAPIDemoApplication {

    public static void main(String[] args) {

		try {
        OWLModel owlModel = ProtegeOWL.createJenaOWLModel();
        owlModel.getNamespaceManager().setDefaultNamespace("http://hello.com#");
        OWLNamedClass worldClass = owlModel.createOWLNamedClass("World");
        System.out.println("Class URI: " + worldClass.getURI());
       }
				 catch (OntologyLoadException oe){
					oe.printStackTrace();
				}
    }
}

