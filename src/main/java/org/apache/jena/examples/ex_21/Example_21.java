package org.apache.jena.examples.ex_21;

import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.Location;
import com.hp.hpl.jena.tdb.store.DatasetGraphTDB;
import com.hp.hpl.jena.util.FileManager;

public class Example_21 {

    public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(Example_21.class.getClassLoader());
        InputStream in = fm.open("org/apache/jena/examples/ex_20/data.nt");

        Location location = new Location ("tmp/TDB");
        DatasetGraphTDB dsg = TDBFactory.createDatasetGraph(location);

        TDBLoader.load(dsg, in, false);
        
        String queryString = 
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
            "SELECT ?name WHERE { " +
            "    ?person foaf:mbox <mailto:alice@example.org> . " +
            "    ?person foaf:name ?name . " +
            "}";
        
        Lock lock = dsg.getLock();
        lock.enterCriticalSection(Lock.READ);
        try {
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.create(query, dsg.toDataset());
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
        } finally {
            lock.leaveCriticalSection();
        }

        dsg.close();
    }

}
