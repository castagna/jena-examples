package org.apache.jena.examples;

import java.io.InputStream;

import org.apache.clerezza.rdf.core.Graph;
import org.apache.clerezza.rdf.core.serializedform.Parser;
import org.apache.clerezza.rdf.core.serializedform.Serializer;
import org.apache.clerezza.rdf.core.serializedform.SupportedFormat;

public class ExampleClerezza_01 {

	// Thanks to Reto
	public static void main(String[] args) {
		InputStream in = Utils.getResourceAsStream("data/data.ttl");
		Parser parser = Parser.getInstance(); 
		Graph g = parser.parse(in, SupportedFormat.TURTLE);
		Serializer serializer = Serializer.getInstance(); 
		serializer.serialize(System.out, g, SupportedFormat.N_TRIPLE);
	}
	
}
