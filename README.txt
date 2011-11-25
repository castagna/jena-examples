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

Miscellaneous examples are: ExampleClerezza_NN.java, ExampleJenaJUNG_NN.java,
ExampleRDFa_NN.java, etc.


Requirements
------------

The only requirements are a Java JDK 1.6 and Apache Maven.

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
understand which jar is required by other jar files:


jena-core (-> jena-iri):

+- org.apache.jena:jena-core:jar:2.6.5-incubating-SNAPSHOT:compile
|  +- org.apache.jena:jena-iri:jar:0.9.0-incubating-SNAPSHOT:compile
|  +- com.ibm.icu:icu4j:jar:3.4.4:compile
|  \- xerces:xercesImpl:jar:2.7.1:compile

jena-arq (-> jena-core -> jena-iri):

+- org.apache.jena:jena-arq:jar:2.8.9-incubating-SNAPSHOT:compile
|  +- org.codehaus.woodstox:wstx-asl:jar:3.2.9:compile
|  |  \- stax:stax-api:jar:1.0.1:compile
|  +- commons-codec:commons-codec:jar:1.5:compile
|  +- org.apache.httpcomponents:httpclient:jar:4.1.2:compile
|  \- org.apache.httpcomponents:httpcore:jar:4.1.2:compile

jena-tdb (-> jena-arq -> jena-core -> jena-iri):

+- org.apache.jena:jena-tdb:jar:0.9.0-incubating-SNAPSHOT:compile

jena-larq (-> jena-arq -> jena-core -> jena-iri):

+- org.apache.jena:jena-larq:jar:0.2.2-incubating-SNAPSHOT:compile
|  \- org.apache.lucene:lucene-core:jar:3.1.0:compile

java-rdfa:

+- net.rootdev:java-rdfa:jar:0.4.2-RC2:compile
|  \- com.hp.hpl.jena:iri:jar:0.8:compile
+- net.rootdev:java-rdfa-htmlparser:jar:0.4.2-RC2:compile
|  \- nu.validator.htmlparser:htmlparser:jar:1.2.1:compile

JenaJung:

+- net.rootdev:JenaJung:jar:0.1.1:compile
|  +- net.sf.jung:jung-jai:jar:2.0.1:compile
|  |  +- net.sf.jung:jung-api:jar:2.0.1:compile
|  |  \- net.sf.jung:jung-visualization:jar:2.0.1:compile
|  |     \- net.sf.jung:jung-algorithms:jar:2.0.1:compile
|  |        \- colt:colt:jar:1.2.0:compile
|  |           \- concurrent:concurrent:jar:1.3.4:compile
|  \- net.sf.jung:jung-graph-impl:jar:2.0.1:compile
|     \- net.sourceforge.collections:collections-generic:jar:4.01:compile

Clerezza:

+- org.apache.clerezza:rdf.jena.parser:jar:0.11-incubating-SNAPSHOT:compile
|  +- org.apache.clerezza:rdf.jena.facade:jar:0.13-incubating-SNAPSHOT:compile
|  |  \- org.wymiwyg:wymiwyg-commons-core:jar:0.7.6:compile
|  |     +- commons-logging:commons-logging-api:jar:1.1:compile
|  |     \- javax.activation:activation:jar:1.1:compile
|  +- org.apache.felix:org.apache.felix.scr.annotations:jar:1.5.0:compile
|  \- org.apache.clerezza.ext:slf4j-scala-api:jar:1.6.2-incubating-SNAPSHOT:compile
|     \- org.scala-lang:scala-library:jar:2.8.1:compile
+- org.apache.clerezza:rdf.jena.serializer:jar:0.10-incubating-SNAPSHOT:compile
+- org.apache.clerezza:rdf.jena.commons:jar:0.6-incubating-SNAPSHOT:compile
|  \- org.apache.clerezza:rdf.core:jar:0.13-incubating-SNAPSHOT:compile
|     +- org.osgi:org.osgi.core:jar:4.2.0:compile
|     +- org.osgi:org.osgi.compendium:jar:4.2.0:compile
|     \- org.apache.clerezza:utils:jar:0.2-incubating-SNAPSHOT:compile

slf4j + log4j:

+- org.slf4j:slf4j-api:jar:1.6.4:compile
+- org.slf4j:slf4j-log4j12:jar:1.6.4:compile
\- log4j:log4j:jar:1.2.16:compile



   Have fun!
