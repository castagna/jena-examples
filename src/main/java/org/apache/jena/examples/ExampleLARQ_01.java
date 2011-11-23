package org.apache.jena.examples;

import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.larq.IndexLARQ;
import org.apache.jena.larq.assembler.AssemblerLARQ;
import org.apache.lucene.index.CorruptIndexException;

import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.Location;
import com.hp.hpl.jena.tdb.store.DatasetGraphTDB;
import com.hp.hpl.jena.util.FileManager;

public class ExampleLARQ_01 {

	public static void main(String[] args) throws CorruptIndexException, IOException {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(ExampleTDB_01.class.getClassLoader());
        InputStream in = fm.open("data/data.nt");

        Location location = new Location ("tmp/TDB");
        // build the Lucene index when pointed to a non existing directory
        DatasetGraphTDB dsg = (DatasetGraphTDB)TDBFactory.createDatasetGraph(location);

        TDBLoader.load(dsg, in, false);
        
        IndexLARQ index = AssemblerLARQ.make(dsg.toDataset(), "tmp/lucene");
        NodeIterator iter = index.searchModelByIndex("A*"); // use Lucene syntax here
        while ( iter.hasNext() ) {
        	System.out.println(iter.next());
        }
	}

}
