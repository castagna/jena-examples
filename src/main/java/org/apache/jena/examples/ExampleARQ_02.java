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
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.ResultSetRewindable;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class ExampleARQ_02 {

    public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(ExampleARQ_02.class.getClassLoader());
        Model model = FileManager.get().loadModel("data/data.ttl");

        String queryString = "SELECT * { ?s ?p ?o }";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSetRewindable results = ResultSetFactory.makeRewindable(qexec.execSelect());

            System.out.println("---- XML ----");
            ResultSetFormatter.outputAsXML(System.out, results);
            results.reset();

            System.out.println("---- Text ----");
            ResultSetFormatter.out(System.out, results);
            results.reset();

            System.out.println("\n---- CSV ----");
            ResultSetFormatter.outputAsCSV(System.out, results);
            results.reset();

            System.out.println("\n---- TSV ----");
            ResultSetFormatter.outputAsTSV(System.out, results);
            results.reset();
            
            System.out.println("\n---- JSON ----");
            ResultSetFormatter.outputAsJSON(System.out, results);
            results.reset();
        } finally {
            qexec.close();
        }
    }

}
