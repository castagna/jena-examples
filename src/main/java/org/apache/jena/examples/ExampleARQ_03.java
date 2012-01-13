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

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import com.hp.hpl.jena.util.FileManager;

public class ExampleARQ_03 {

	public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(ExampleARQ_01.class.getClassLoader());
        String apikey = System.getenv("KASABI_API_KEY");
        
        String queryString = 
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
        		"PREFIX italy: <http://data.kasabi.com/dataset/italy/schema/>" +
        		"SELECT ?region WHERE { " +
        		"	?region rdf:type italy:Region" +
        		"}";
        Query query = QueryFactory.create(queryString);
        QueryEngineHTTP qexec = (QueryEngineHTTP)QueryExecutionFactory.createServiceRequest("http://api.kasabi.com/dataset/italy/apis/sparql", query);
        qexec.addParam("apikey", apikey);
        try {
            ResultSet results = qexec.execSelect();
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                Resource region = soln.getResource("region");
                System.out.println(region.getURI());
            }
        } finally {
            qexec.close();
        }
	}

}
