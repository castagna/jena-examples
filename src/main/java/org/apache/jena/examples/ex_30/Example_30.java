package org.apache.jena.examples.ex_30;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class Example_30 {

    public static void main(String[] args) {
        String sourceURL = "http://www.eswc2006.org/technologies/ontology";
        String namespace = sourceURL + "#";
        OntModel base = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        base.read( sourceURL, "RDF/XML" );

        OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, base );

        OntClass paperClass = base.getOntClass( namespace + "Paper" );
        Individual paper = base.createIndividual( namespace + "paper1", paperClass );

        System.out.println("---- Assertions in the data ----");
        for (Iterator<Resource> i = paper.listRDFTypes(false); i.hasNext(); ) {
            System.out.println( paper.getURI() + " is a " + i.next() );
        }

        System.out.println("\n---- Inferred assertions ----");
        paper = inf.getIndividual( namespace + "paper1" );
        for (Iterator<Resource> i = paper.listRDFTypes(false); i.hasNext(); ) {
            System.out.println( paper.getURI() + " is a " + i.next() );
        }
    }

}
