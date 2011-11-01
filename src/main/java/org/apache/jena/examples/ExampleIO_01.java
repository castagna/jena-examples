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

import org.openjena.riot.RIOT;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ExampleIO_01 {

    public static void main(String[] args) {
        InputStream in = Utils.getResourceAsStream("data/data.ttl");
        
        RIOT.init() ;

        Model model = ModelFactory.createDefaultModel(); // creates an in-memory Jena Model
        model.read(in, null, "TURTLE"); // parses an InputStream assuming RDF in Turtle format
        
        // Write the Jena Model in Turtle, RDF/XML and N-Triples format
        System.out.println("\n---- Turtle ----");
        model.write(System.out, "TURTLE");
        System.out.println("\n---- RDF/XML ----");
        model.write(System.out, "RDF/XML");
        System.out.println("\n---- RDF/XML Abbreviated ----");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println("\n---- N-Triples ----");
        model.write(System.out, "N-TRIPLES");
        System.out.println("\n---- RDF/JSON ----");
        model.write(System.out, "RDF/JSON");
    }
    
}
