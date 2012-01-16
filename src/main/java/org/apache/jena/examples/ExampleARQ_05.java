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

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class ExampleARQ_05 {

	public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(ExampleARQ_01.class.getClassLoader());
        Model model = FileManager.get().loadModel("data/data.ttl");
        System.out.println("Input data:");
        model.write(System.out, "TURTLE");
        
        File path = new File("src/main/resources/data/queries");
        File[] files = path.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("construct-") && name.endsWith(".sparql");
			}
		});
        Arrays.sort(files);

        for (File file : files) {
        	System.out.println("Executing " + file.getName() + " ...");
        	Query query = QueryFactory.read(file.getAbsolutePath());
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            try {
                Model result = qexec.execConstruct();
                model.add(result);
            } finally {
                qexec.close();
            }
		}

        System.out.println("Output data:");
        model.write(System.out, "TURTLE");
	}

}
