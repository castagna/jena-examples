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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

public class ExampleARQ_04 {

	public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(ExampleARQ_01.class.getClassLoader());
        String uri = "http://api.kasabi.com/dataset/italy/apis/sparql";
        String apikey = System.getenv("KASABI_API_KEY");

        String queryString = 
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
        		"PREFIX italy: <http://data.kasabi.com/dataset/italy/schema/>" +
        		"SELECT * WHERE { " +
        		"	SERVICE <" + uri + "> { ?region rdf:type italy:Region }" +
        		"}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, ModelFactory.createDefaultModel());

        // Set additional parameters on a per SERVICE basis, see also: JENA-195
        Map<String, Map<String,List<String>>> serviceParams = new HashMap<String, Map<String,List<String>>>();
        Map<String,List<String>> params = new HashMap<String,List<String>>();
        List<String> values = new ArrayList<String>();
        values.add(apikey);
        params.put("apikey", values);
        serviceParams.put(uri, params);
        qexec.getContext().set(ARQ.serviceParams, serviceParams);

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
