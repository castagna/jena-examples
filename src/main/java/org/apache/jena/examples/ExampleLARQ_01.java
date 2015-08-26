package org.apache.jena.examples;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

public class ExampleLARQ_01 {

	public static void main(String[] args) throws CorruptIndexException, IOException {
//        FileManager fm = FileManager.get();
//        fm.addLocatorClassLoader(ExampleTDB_01.class.getClassLoader());
//        InputStream in = fm.open("data/data.nt");
//
//        Location location = Location.create ("tmp/TDB");
//        // build the Lucene index when pointed to a non existing directory
//        DatasetGraphTDB dsg = (DatasetGraphTDB)TDBFactory.createDatasetGraph(location);
//
//        TDBLoader.load(dsg, in, false);
//        
//        IndexLARQ index = AssemblerLARQ.make(dsg.toDataset(), "tmp/lucene");
//        NodeIterator iter = index.searchModelByIndex("A*"); // use Lucene syntax here
//        while ( iter.hasNext() ) {
//        	System.out.println(iter.next());
//        }
	}

}
