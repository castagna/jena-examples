package org.apache.jena.examples.ex_20;

import java.io.InputStream;
import java.util.Iterator;

import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.Location;
import com.hp.hpl.jena.tdb.store.DatasetGraphTDB;
import com.hp.hpl.jena.util.FileManager;

public class Example_20 {

    public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(Example_20.class.getClassLoader());
        InputStream in = fm.open("org/apache/jena/examples/ex_20/data.nt");

        Location location = new Location ("tmp/TDB");
        DatasetGraphTDB dsg = TDBFactory.createDatasetGraph(location);

        TDBLoader.load(dsg, in, false);
        
        Lock lock = dsg.getLock();
        lock.enterCriticalSection(Lock.READ);
        try {
            Iterator<Quad> iter = dsg.find();
            while ( iter.hasNext() ) {
                Quad quad = iter.next();
                System.out.println(quad);
            }
        } finally {
            lock.leaveCriticalSection();
        }

        dsg.close();
    }

}
