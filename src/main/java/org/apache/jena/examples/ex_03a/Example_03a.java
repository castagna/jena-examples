package org.apache.jena.examples.ex_03a;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class Example_03a {

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        
        Resource alice = ResourceFactory.createResource("http://example.org/alice");
        
        Resource bob = ResourceFactory.createResource("http://example.org/bob");
        model.add (alice, RDF.type, FOAF.Person);
        model.add (alice, FOAF.name, "Alice");
        model.add (alice, FOAF.mbox, ResourceFactory.createResource("mailto:alice@example.org"));
        model.add (alice, FOAF.knows, bob);

        model.write(System.out, "TURTLE");
    }

}
