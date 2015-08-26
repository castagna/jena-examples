Apache Jena - Examples
======================

This is a collection of small and simple examples on how to use Apache Jena
to handle RDF data.


How examples are organised
--------------------------

Examples are grouped by name: ExampleX_NN.java and are progressively numbered
from the simplest to the slightly more advanced ones.

A good learning path is to start from the ExampleIO_NN.java which show how to
use Apache Jena to read (i.e. parse) or write (i.e. serialize) RDF data in
various format. Apache Jena supports RDF/XML, N3, Turtle, N-Triples, etc.
RIOT is a new parsing subsystem for Jena, if you are interested you can look
at the ExampleRIOT_NN.java files.

The examples named ExampleAPI_NN.java show how to use the Jena Model APIs to
create or manipulate RDF data.

SPARQL is the query language used for data in RDF stores and ARQ is the query
engine included in Jena. The examples named ExampleARQ_NN.java show how to run
SPARQL queries and iterate through the results.
An extension for ARQ to run free text searches is called LARQ (i.e. Lucene +
ARQ), if you are interested, you can look at ExampleLARQ_NN.java files.

TDB is the native store included in Apache Jena. The examples named 
ExampleTDB_NN.java files show how to load data into TDB and run SPARQL
queries.

If you are interested in ontologies and inference look at ExampleONT_NN.java
and ExampleINF_NN.java.


Requirements
------------

The only requirements are a Java JDK 1.8 and Apache Maven.

Instructions on how to install Maven are here:
http://maven.apache.org/download.html#Installation 

If you use Eclipse, it is recommended to set M2_REPO in Eclipse (once):

  mvn -Declipse.workspace=/path/to/your/workspace eclipse:add-maven-repo

Once you have Maven installed, verify with:

  mvn -version
  
Then run (once) this to download all the necessary dependencies:

  cd jena-example
  mvn package

You can import the project in Eclipse via
File > Import... > Existing Projects into Workspace

You can run mvn eclipse:eclipse to re-generate Eclipse .project and .classpath
files automatically from your pom.xml file.

You can run mvn dependency:copy-dependencies to copy all the *.jar files in
the target/dependency/ directory.

You can run mvn dependency:tree to visualize a tree of all the dependency and
understand which jar is required by other jar files.


   Have fun!
