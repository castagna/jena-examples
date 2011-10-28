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

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class ExampleAPI_02 {

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        
        model.createResource("http://example.org/alice", FOAF.Person)
            .addProperty(FOAF.name, "Alice")
            .addProperty(FOAF.mbox, model.createResource("mailto:alice@example.org"))
            .addProperty(FOAF.knows, model.createResource("http://example.org/bob"));

        model.write(System.out, "TURTLE");
    }

}
