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

package org.apache.jena.examples;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.GraphListener;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.sparql.util.graph.GraphListenerBase;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.apache.jena.tdb.store.DatasetGraphTDB;
import org.apache.jena.tdb.sys.TDBInternal;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;

public class ExampleTDB_04 {

    public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(ExampleTDB_04.class.getClassLoader());
        InputStream in = fm.open("data/data.nt");

        DatasetGraphTDB dsg =  TDBInternal.getBaseDatasetGraphTDB(TDBFactory.createDatasetGraph());

        GraphListener listener = new MyListener();
        dsg.getDefaultGraph().getEventManager().register(listener);
        Iterator<Node> iter = dsg.listGraphNodes();
        while ( iter.hasNext() ) {
        	Graph graph = dsg.getGraph(iter.next());
        	graph.getEventManager().register(listener);
        }

        // this does not send events to the GraphListener
        TDBLoader.load(dsg, in, false);
        
        // this does not uses the Graph SPI
        dsg.add(new Quad(Quad.defaultGraphIRI, NodeFactory.createURI("x"), NodeFactory.createURI("y"), NodeFactory.createURI("z")));
        
        // this does
        Graph graph = dsg.getDefaultGraph();
        graph.add(new Triple(NodeFactory.createURI("x"), NodeFactory.createURI("y"), NodeFactory.createURI("z")));

        // this sends events to the GraphListener
        Model model = ModelFactory.createModelForGraph(dsg.getDefaultGraph()) ;
        model.add(ResourceFactory.createProperty("foo:x1"), RDFS.label, "X1");

        // this does it too
        in = fm.open("data/data.nt");
        model.read(in, "", "N-TRIPLES");

        dsg.close();
    }

    static class MyListener extends GraphListenerBase implements GraphListener {
		@Override protected void addEvent(Triple t) {
			System.out.println(t);
		}
		@Override protected void deleteEvent(Triple t) {
			System.out.println(t);
		}
    }

}
