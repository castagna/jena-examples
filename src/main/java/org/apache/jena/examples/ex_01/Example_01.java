package org.apache.jena.examples.ex_01;

import java.io.File;
import java.io.InputStream;

import org.openjena.riot.RIOT;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Example_01 {

    public static void main(String[] args) {
        InputStream in = getResourceAsStream(Example_01.class, "data.ttl");
        
        RIOT.init() ;

        Model model = ModelFactory.createDefaultModel(); // creates an in-memory Jena Model
        model.read(in, null, "TURTLE"); // parses an InputStream assuming RDF in Turtle format
        
        // Write the Jena Model in Turtle, RDF/XML and N-Triples format
        System.out.println("\n---- Turtle ----");
        model.write(System.out, "TURTLE");
        System.out.println("\n---- RDF/XML ----");
        model.write(System.out, "RDF/XML");
        System.out.println("\n---- RDF/XML Abbreviated ----");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println("\n---- N-Triples ----");
        model.write(System.out, "N-TRIPLES");
    }

    public static InputStream getResourceAsStream(Class<?> clazz, String filename) {
        String pkg = clazz.getPackage().getName().replaceAll("\\.", File.separator); 
        InputStream in = clazz.getClassLoader().getResourceAsStream(pkg + File.separator + filename);
        return in;
    }
    
}
