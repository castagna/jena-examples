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

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class ExampleDataTypes_01 {

    private static final String BASE = "http://example.org/"; 
    
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        
        Resource subject = r("s");
        
        model.addLiteral (subject, p("p1"), 10);
        model.addLiteral (subject, p("p2"), 0.5);
        model.addLiteral (subject, p("p3"), (float)0.5);
        model.addLiteral (subject, p("p4"), l(20));
        model.addLiteral (subject, p("p5"), l(0.99));
        model.addLiteral (subject, p("p6"), true);
        model.add (subject, p("p7"), l("2012-03-11", XSDDatatype.XSDdate));
        model.add (subject, p("p8"), l("P2Y", XSDDatatype.XSDduration));

        model.setNsPrefix("example", BASE);
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

        model.write(System.out, "TURTLE");
    }
    
    private static Resource r ( String localname ) {
        return ResourceFactory.createResource ( BASE + localname );
    }
    
    private static Property p ( String localname ) {
        return ResourceFactory.createProperty ( BASE, localname );
    }

    private static Literal l ( Object value ) {
        return ResourceFactory.createTypedLiteral ( value );
    }

    private static Literal l ( String lexicalform, RDFDatatype datatype ) {
        return ResourceFactory.createTypedLiteral ( lexicalform, datatype );
    }

}
