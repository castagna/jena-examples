package org.apache.jena.examples;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ExampleRDFa_01 {

	public static void main(String[] args) throws ClassNotFoundException {
		// This is to ensure the Jena RDFa reader is wired into Jena
		Class.forName("net.rootdev.javardfa.jena.RDFaReader");
		
		Model model = ModelFactory.createDefaultModel();
		// Remember screen scraping hell? Screen scraping Addio!
		model.read("http://www.rottentomatoes.com/m/apache/", "HTML");
		model.write(System.out, "TURTLE");
	}

}
