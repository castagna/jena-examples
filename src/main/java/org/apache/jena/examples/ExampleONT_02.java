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

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.util.FileManager;

public class ExampleONT_02 {

    public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(ExampleONT_02.class.getClassLoader());
        
        Model tbox = FileManager.get().loadModel("data/inference/tbox.owl", null, "RDF/XML"); // http://en.wikipedia.org/wiki/Tbox
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner().bindSchema(tbox.getGraph());
        Model abox = FileManager.get().loadModel("data/inference/abox.owl", null, "RDF/XML"); // http://en.wikipedia.org/wiki/Abox

        InfModel inf = ModelFactory.createInfModel(reasoner, abox);

        ValidityReport validityReport = inf.validate(); 

        if ( !validityReport.isValid() ) {
        	System.out.println("Inconsistent");
            Iterator<Report> iter = validityReport.getReports();
            while ( iter.hasNext() ) {
            	Report report = iter.next();
            	System.out.println(report);
            }        	
        } else {
        	System.out.println("Valid");
        }

    }

}
