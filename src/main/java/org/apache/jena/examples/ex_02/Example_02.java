package org.apache.jena.examples.ex_02;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Example_02 {

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        // Retrieve a URL corresponding to RDF Schema vocabulary in RDF/XML format
        model.read("http://www.w3.org/TR/rdf-schema/rdfs-namespace", null, "RDF/XML");

        model.write(System.out, "TURTLE");
    }

}
