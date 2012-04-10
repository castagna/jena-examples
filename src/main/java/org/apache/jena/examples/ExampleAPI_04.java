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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class ExampleAPI_04 {

    public static void main(String[] args) throws IOException {
        FileManager.get().addLocatorClassLoader(ExampleAPI_04.class.getClassLoader());
        InputStream in = FileManager.get().open("data/data2.ttl");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        Model all = ModelFactory.createDefaultModel();
        while ( ( line = reader.readLine() ) != null ) {
        	ByteArrayInputStream bais = new ByteArrayInputStream(line.getBytes());
        	Model model = ModelFactory.createDefaultModel();
        	model.read(bais, null, "TURTLE");
        	model.write(System.out, "TURTLE");
        	all.add(model);
        	all.setNsPrefixes(model.getNsPrefixMap());
        	System.out.println("----------------");
        }
        all.write(System.out, "TURTLE");
    }

}
