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
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class ExampleARQ_07 {

    public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(ExampleARQ_07.class.getClassLoader());
        Model model = FileManager.get().loadModel("data/data.ttl");

        String queryString = 
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
        		"SELECT ?name WHERE { " +
        		"    ?person foaf:mbox <mailto:alice@example.org> . " +
        		"    ?person foaf:name ?name . " +
        		"}";
        Model m1 = query (model, queryString);
        Model m2 = query (model, queryString);
        System.out.println(m1.isIsomorphicWith(m2));
    }
    
    private static Model query ( Model model, String queryString ) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        Model result = ModelFactory.createDefaultModel();
        try {
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.asRDF(result, results);
        } finally {
            qexec.close();
        }
        return result;
    }

}
