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

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class ExampleONT_01 {

    public static void main(String[] args) {
        String sourceURL = "http://www.eswc2006.org/technologies/ontology";
        String namespace = sourceURL + "#";
        OntModel base = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        base.read( sourceURL, "RDF/XML" );

        OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, base );

        OntClass paperClass = base.getOntClass( namespace + "Paper" );
        Individual paper = base.createIndividual( namespace + "paper1", paperClass );

        System.out.println("---- Assertions in the data ----");
        for (Iterator<Resource> i = paper.listRDFTypes(false); i.hasNext(); ) {
            System.out.println( paper.getURI() + " is a " + i.next() );
        }

        System.out.println("\n---- Inferred assertions ----");
        paper = inf.getIndividual( namespace + "paper1" );
        for (Iterator<Resource> i = paper.listRDFTypes(false); i.hasNext(); ) {
            System.out.println( paper.getURI() + " is a " + i.next() );
        }
    }

}
