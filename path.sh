#!/bin/bash

export JAR_HOME=/home/blanca/Protege_3.4.4
export JAR_PLUGINS=/home/blanca/Protege_3.4.4/plugins/edu.stanford.smi.protegex.owl

for f in $JAR_HOME/*.jar
		
do
		A=$A:$f 
done

for g in $JAR_PLUGINS/*.jar
		
do
   B=$B:$g
done

JAR_CLASSPATH=$A$B:"/home/blanca/cenidpd/talkprotege"
export JAR_CLASSPATH

#echo the classpath $JAR_CLASSPATH

javac -classpath $JAR_CLASSPATH OWLAPIDemoApplication.java
java -Dprotege.dir=$JAR_HOME -classpath $JAR_CLASSPATH OWLAPIDemoApplication

#javac -classpath $JAR_CLASSPATH JenaMain.java
#java -Dprotege.dir=$JAR_HOME -classpath $JAR_CLASSPATH JenaMain
