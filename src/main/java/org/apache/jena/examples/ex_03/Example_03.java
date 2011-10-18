package org.apache.jena.examples.ex_03;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class Example_03 {

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        
        model.createResource("http://example.org/alice", FOAF.Person)
            .addProperty(FOAF.name, "Alice")
            .addProperty(FOAF.mbox, model.createResource("mailto:alice@example.org"))
            .addProperty(FOAF.knows, model.createResource("http://example.org/bob"));

        model.write(System.out, "TURTLE");
    }

}
