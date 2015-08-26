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

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

public class ExampleAPI_03 {

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        
        Resource alice = ResourceFactory.createResource("http://example.org/alice");
        
        Resource bob = ResourceFactory.createResource("http://example.org/bob");
        model.add (alice, RDF.type, FOAF.Person);
        model.add (alice, FOAF.name, "Alice");
        model.add (alice, FOAF.mbox, ResourceFactory.createResource("mailto:alice@example.org"));
        model.add (alice, FOAF.knows, bob);

        model.write(System.out, "TURTLE");
    }

}
