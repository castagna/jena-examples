/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;
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
