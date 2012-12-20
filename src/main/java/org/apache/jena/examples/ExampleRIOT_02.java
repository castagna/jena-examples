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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.atlas.lib.Sink;
import org.apache.jena.atlas.lib.SinkNull;
import org.apache.jena.atlas.lib.SinkWrapper;
import org.openjena.riot.Lang;
import org.openjena.riot.RiotReader;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.vocabulary.RDF;

public class ExampleRIOT_02 {

    public static void main(String[] args) {
        InputStream in = Utils.getResourceAsStream("data/data.nq");
        SinkQuadStats sink = new SinkQuadStats(new SinkNull<Quad>());
        RiotReader.parseQuads(in, Lang.NQUADS, null, sink);
        
        System.out.println("---- Classes ----");
        print ( sink.getClasses() );
        
        System.out.println("---- Properties ----");
        print ( sink.getProperties() );
        
        System.out.println("---- Namespaces ----");
        print ( sink.getNamespaces() );
        
        System.out.println();
        printPerGraph ( sink.getClassesPerGraph() );

        System.out.println();
        printPerGraph ( sink.getPropertiesPerGraph() );

        System.out.println();
        printPerGraph ( sink.getNamespacesPerGraph() );
    }
    
    private static void print ( Map<Node, Integer> map ) {
    	Iterator<Node> iter = map.keySet().iterator() ;
    	while ( iter.hasNext() ) {
    		Node node = iter.next() ;
    		System.out.println(node + " = " + map.get(node));
    	}
    }

    private static void printPerGraph ( Map<Node, Map<Node, Integer>> map ) {
    	Iterator<Node> iter = map.keySet().iterator() ;
    	while ( iter.hasNext() ) {
    		Node graph = iter.next() ;
    		System.out.println ("---- " + graph + " ----");
    		print ( map.get(graph) );
    	}
    }

}

class SinkQuadStats extends SinkWrapper<Quad> {

	private long count = 0 ;
	private Map<Node, Integer> classes = new HashMap<Node, Integer>() ;
	private Map<Node, Integer> properties = new HashMap<Node, Integer>() ;
	private Map<Node, Integer> namespaces = new HashMap<Node, Integer>() ;
	private Map<Node, Map<Node, Integer>> classesPerGraph = new HashMap<Node, Map<Node, Integer>>() ;
	private Map<Node, Map<Node, Integer>> propertiesPerGraph = new HashMap<Node, Map<Node, Integer>>() ;
	private Map<Node, Map<Node, Integer>> namespacesPerGraph = new HashMap<Node, Map<Node, Integer>>() ;
    
    public SinkQuadStats(Sink<Quad> output) { 
    	super(output) ; 
    }
    
    public SinkQuadStats() { 
    	super(new SinkNull<Quad>()) ; 
    }
    
    @Override
    public void send(Quad quad) {
        count++ ;

        Node g = quad.getGraph();
        
        Node p = quad.getPredicate();
        Node ns = Node.createURI(p.getNameSpace());
    	increment ( properties, p );
    	increment ( namespaces, ns );
    	increment ( propertiesPerGraph, g, p );
    	increment ( namespacesPerGraph, g, ns );
        
        if ( p.equals(RDF.type.asNode()) ) {
        	Node o = quad.getObject();
        	if ( o.isURI() ) {
                ns = Node.createURI(o.getNameSpace());
            	increment ( classes, o );
            	increment ( namespaces, ns );
            	increment ( classesPerGraph, g, o );
            	increment ( namespacesPerGraph, g, ns );
        	}
        }

        super.send(quad) ;
    }

    private void increment (Map<Node,Integer> map, Node key) {
        if ( map.containsKey(key) ) {
        	int c = map.get(key) + 1;
        	map.put(key, c);
        } else {
        	map.put(key, 1);
        }
    }
    
    private void increment (Map<Node, Map<Node, Integer>> map, Node graph, Node key) {
        if ( map.containsKey(graph) ) {
        	increment (map.get(graph), key);
        } else {
        	map.put(graph, new HashMap<Node,Integer>());
        	increment (map.get(graph), key);
        }
    }
    
    public long getCount() { 
    	return count ; 
    }
    
    public Map<Node, Integer> getProperties() {
    	return properties ;
    }
    
    public Map<Node, Integer> getClasses() {
    	return classes ;
    }

    public Map<Node, Integer> getNamespaces() {
    	return namespaces ;
    }

    public Map<Node, Map<Node, Integer>> getPropertiesPerGraph() {
    	return propertiesPerGraph ;
    }
    
    public Map<Node, Map<Node, Integer>> getClassesPerGraph() {
    	return classesPerGraph ;
    }

    public Map<Node, Map<Node, Integer>> getNamespacesPerGraph() {
    	return namespacesPerGraph ;
    }

}
