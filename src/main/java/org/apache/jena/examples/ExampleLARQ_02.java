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

public class ExampleLARQ_02 {

	public static void main(String[] args) throws Exception {
//        FileManager fm = FileManager.get();
//        fm.addLocatorClassLoader(ExampleTDB_01.class.getClassLoader());
//        InputStream in = fm.open("data/data.nt");
//                
//        Location location = Location.create ("tmp/TDB");
//        DatasetGraphTDB dsg = (DatasetGraphTDB)TDBFactory.createDatasetGraph(location);
//
//        TDBLoader.load(dsg, in, false); // load data into TDB
//        // build the Lucene index when pointed to a non existing directory
//        AssemblerLARQ.make(dsg.toDataset(), "tmp/lucene"); 
//
//        String queryString = 
//            "PREFIX pf: <http://jena.hpl.hp.com/ARQ/property#>" + 
//            "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
//    		"SELECT ?name ?email WHERE { " +
//    		"    ?person foaf:name ?name . " +
//    		"    ?name pf:textMatch '*:*' . " + // use the Lucene syntax here
//    		"    OPTIONAL { ?person foaf:mbox ?email . }" +
//    		"}";
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qexec = QueryExecutionFactory.create(query, dsg.toDataset());
//        try {
//        	ResultSet results = qexec.execSelect();
//        	while ( results.hasNext() ) {
//        		QuerySolution soln = results.nextSolution();
//        		System.out.println(soln);
//        	}
//        } finally {
//        	qexec.close();
//        }
	}

}
