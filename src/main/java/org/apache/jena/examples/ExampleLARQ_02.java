package org.apache.jena.examples;

import java.io.InputStream;

import org.apache.jena.larq.assembler.AssemblerLARQ;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.Location;
import com.hp.hpl.jena.tdb.store.DatasetGraphTDB;
import com.hp.hpl.jena.util.FileManager;

public class ExampleLARQ_02 {

	public static void main(String[] args) throws Exception {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(ExampleTDB_01.class.getClassLoader());
        InputStream in = fm.open("data/data.nt");
                
        Location location = new Location ("tmp/TDB");
        DatasetGraphTDB dsg = (DatasetGraphTDB)TDBFactory.createDatasetGraph(location);

        TDBLoader.load(dsg, in, false); // load data into TDB
        // build the Lucene index when pointed to a non existing directory
        AssemblerLARQ.make(dsg.toDataset(), "tmp/lucene"); 

        String queryString = 
            "PREFIX pf: <http://jena.hpl.hp.com/ARQ/property#>" + 
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
    		"SELECT ?name ?email WHERE { " +
    		"    ?person foaf:name ?name . " +
    		"    ?name pf:textMatch '*:*' . " + // use the Lucene syntax here
    		"    OPTIONAL { ?person foaf:mbox ?email . }" +
    		"}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, dsg.toDataset());
        try {
        	ResultSet results = qexec.execSelect();
        	while ( results.hasNext() ) {
        		QuerySolution soln = results.nextSolution();
        		System.out.println(soln);
        	}
        } finally {
        	qexec.close();
        }
	}

}
