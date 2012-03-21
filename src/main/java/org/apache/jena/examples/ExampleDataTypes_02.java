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

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.datatypes.DatatypeFormatException;
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;

public class ExampleDataTypes_02 {

    public static void main(String[] args) {
        // Register custom datatypes 
        RDFDatatype celsius = TemperatureCelsius.get();
        RDFDatatype fahrenheit = TemperatureFahrenheit.get();
        RDFDatatype kelvin = TemperatureKelvin.get();
        RDFDatatype rankine = TemperatureRankine.get();
        TypeMapper.getInstance().registerDatatype(celsius);
        TypeMapper.getInstance().registerDatatype(fahrenheit);
        TypeMapper.getInstance().registerDatatype(kelvin);
        TypeMapper.getInstance().registerDatatype(rankine);

        // Data
        Model model = ModelFactory.createDefaultModel();
        model.add(ResourceFactory.createResource("x1"), RDF.value, model.createTypedLiteral("25", celsius));
        model.add(ResourceFactory.createResource("x2"), RDF.value, model.createTypedLiteral("15", celsius));
        model.add(ResourceFactory.createResource("x3"), RDF.value, model.createTypedLiteral("25", fahrenheit));
        model.add(ResourceFactory.createResource("x4"), RDF.value, model.createTypedLiteral("25", kelvin));
        model.add(ResourceFactory.createResource("x5"), RDF.value, model.createTypedLiteral("25", rankine));
        System.out.println("\n---- Data ----");
        model.write(System.out, "N-TRIPLES");

        // Query and ORDER BY temperature
        String queryString = 
            "PREFIX java: <java:org.apache.jena.examples.>\n" + 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "SELECT * WHERE {\n" +
            "    ?s rdf:value ?temperature .\n" +
            "}\n" +
            "ORDER BY java:temperature( ?temperature )";
        System.out.println("\n---- Query ----");
        System.out.println(queryString);
        
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet results = qexec.execSelect();
            System.out.println("\n---- Results ----");
            System.out.println(ResultSetFormatter.asText(results));
        } finally {
            qexec.close();
        }
    }

}

abstract class AbstractTemperatureType extends BaseDatatype {

    public AbstractTemperatureType(String uri) {
        super(uri);
    }

    @Override
    public Class<?> getJavaClass() {
        return Double.class;
    }

    @Override
    public String unparse(Object value) {
        return value.toString();
    }

    @Override
    public Object parse(String lexicalForm) throws DatatypeFormatException {
        try {
            return new Double(lexicalForm); 
        } catch (NumberFormatException ex) {
            throw new DatatypeFormatException(lexicalForm, this, ex.getMessage());
        }
    }

    @Override public abstract String toString();

}

class TemperatureCelsius extends AbstractTemperatureType {

    private static TemperatureCelsius datatype = new TemperatureCelsius();
    public static TemperatureCelsius get() { return datatype; }
    
    public TemperatureCelsius() {
        super("http://jena.apache.org/datatypes/temperature/celsius");
    }

    @Override
    public String toString() {
        return "Temperature in °C";
    }
    
}

class TemperatureFahrenheit extends AbstractTemperatureType {

    private static TemperatureFahrenheit datatype = new TemperatureFahrenheit();
    public static TemperatureFahrenheit get() { return datatype; }

    
    public TemperatureFahrenheit() {
        super("http://jena.apache.org/datatypes/temperature/fahrenheit");
    }
    
    @Override
    public String toString() {
        return "Temperature in °F";
    }
    
}

class TemperatureKelvin extends AbstractTemperatureType {

    private static TemperatureKelvin datatype = new TemperatureKelvin();
    public static TemperatureKelvin get() { return datatype; }

    
    public TemperatureKelvin() {
        super("http://jena.apache.org/datatypes/temperature/kelvin");
    }
    
    @Override
    public String toString() {
        return "Temperature in K";
    }
    
}

class TemperatureRankine extends AbstractTemperatureType {

    private static TemperatureRankine datatype = new TemperatureRankine();
    public static TemperatureRankine get() { return datatype; }

    
    public TemperatureRankine() {
        super("http://jena.apache.org/datatypes/temperature/rankine");
    }
    
    @Override
    public String toString() {
        return "Temperature in °R";
    }
    
}
