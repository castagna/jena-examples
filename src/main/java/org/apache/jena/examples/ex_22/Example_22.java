package org.apache.jena.examples.ex_22;

import java.io.InputStream;
import java.util.Iterator;

import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.DatasetGraphTxn;
import com.hp.hpl.jena.tdb.ReadWrite;
import com.hp.hpl.jena.tdb.StoreConnection;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.Location;
import com.hp.hpl.jena.util.FileManager;

public class Example_22 {

    public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(Example_22.class.getClassLoader());
        InputStream in = fm.open("org/apache/jena/examples/ex_20/data.nt");

        Location location = new Location ("tmp/TDB");
        StoreConnection sConn = StoreConnection.make(location);

        DatasetGraphTxn dsgTx = null;
        try {
            dsgTx = sConn.begin(ReadWrite.WRITE);
            TDBLoader.load(dsgTx, in, false);
        } catch (Exception e) {
            if ( dsgTx != null ) dsgTx.abort();
        } finally {
            if ( dsgTx != null ) dsgTx.commit();
        }       


        try {
            dsgTx = sConn.begin(ReadWrite.READ);
            Iterator<Quad> iter = dsgTx.find();
            while ( iter.hasNext() ) {
                Quad quad = iter.next();
                System.out.println(quad);
            }
        } catch (Exception e) {
            if ( dsgTx != null ) dsgTx.abort();
        } finally {
            if ( dsgTx != null ) dsgTx.commit();
        }       

        
        
    }

}
