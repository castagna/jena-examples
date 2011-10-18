package org.apache.jena.examples.ex_11;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.ResultSetRewindable;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class Example_11 {

    public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(Example_11.class.getClassLoader());
        Model model = FileManager.get().loadModel("org/apache/jena/examples/ex_01/data.ttl");

        String queryString = "SELECT * { ?s ?p ?o }";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSetRewindable results = ResultSetFactory.makeRewindable(qexec.execSelect());

            System.out.println("---- Text ----");
            ResultSetFormatter.out(System.out, results);
            results.reset();

            System.out.println("\n---- CSV ----");
            ResultSetFormatter.outputAsCSV(System.out, results);
            results.reset();

            System.out.println("\n---- TSV ----");
            ResultSetFormatter.outputAsTSV(System.out, results);
            results.reset();
            
            System.out.println("\n---- JSON ----");
            ResultSetFormatter.outputAsJSON(System.out, results);
            results.reset();
        } finally {
            qexec.close();
        }
    }

}
