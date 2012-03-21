package dev;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openjena.atlas.lib.FileOps;
import org.openjena.riot.Lang;
import org.openjena.riot.RiotLoader;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.sparql.core.DatasetGraph;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.base.file.Location;

public class TestTDBCorruption {

    private static final String path = "target/tdb_corruption";
    private static final String str_literal = "Hello \\uDAE0 World";
    private static final String str_triple = "<s> <p> \"" + str_literal + "\" .";
   
    @Test public void test() {
        Location location = new Location ( path );
        Dataset dataset = TDBFactory.createDataset ( location );
        dataset.begin ( ReadWrite.WRITE );
        try {
            DatasetGraph dsg = dataset.asDatasetGraph();
            DatasetGraph dsg2 = RiotLoader.datasetFromString ( str_triple, Lang.TURTLE, null );
            Iterator<Quad> quads = dsg2.find();
            while ( quads.hasNext() ) {
                Quad quad = quads.next();
                dsg.add(quad);
            }
            dataset.commit();
        } catch ( Exception e ) {
            e.printStackTrace(System.err);
            if ( dataset.isInTransaction() ) dataset.abort();
        } finally {
            dataset.end();
        }
        assertEquals ( 1, dataset.getDefaultModel().size() );
    }
    
}
