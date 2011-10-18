package org.apache.jena.examples.ex_02a;

import org.apache.jena.examples.ex_02b.Example_02b;
import org.openjena.atlas.iterator.Iter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class Example_02a {

    public static void main(String[] args) {
        FileManager fm = FileManager.get(); 
        fm.addLocatorClassLoader(Example_02b.class.getClassLoader());
        // load an RDF file using the FileManager
        Model model = fm.loadModel("org/apache/jena/examples/ex_01/data.ttl", null, "TURTLE");
        model.write(System.out, "TURTLE");
    }

}
