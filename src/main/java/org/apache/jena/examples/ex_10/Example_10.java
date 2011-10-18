package org.apache.jena.examples.ex_10;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class Example_10 {

    public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(Example_10.class.getClassLoader());
        Model model = FileManager.get().loadModel("org/apache/jena/examples/ex_01/data.ttl");

        String queryString = 
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
        		"SELECT ?name WHERE { " +
        		"    ?person foaf:mbox <mailto:alice@example.org> . " +
        		"    ?person foaf:name ?name . " +
        		"}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet results = qexec.execSelect();
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                Literal name = soln.getLiteral("name");
                System.out.println(name);
            }
        } finally {
            qexec.close();
        }

    }

}
