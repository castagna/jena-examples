package org.apache.jena.examples;

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.datatypes.DatatypeFormatException;
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;

public class ExampleDT_01 {

    public static void main(String[] args) {
        RDFDatatype celsius = TemperatureCelsius.get();
        RDFDatatype fahrenheit = TemperatureFahrenheit.get();
        TypeMapper.getInstance().registerDatatype(celsius);
        TypeMapper.getInstance().registerDatatype(fahrenheit);

        Model model = ModelFactory.createDefaultModel();
        model.add(ResourceFactory.createResource("x1"), RDF.value, model.createTypedLiteral("25", celsius));
        model.add(ResourceFactory.createResource("x2"), RDF.value, model.createTypedLiteral("15", celsius));
        model.add(ResourceFactory.createResource("x3"), RDF.value, model.createTypedLiteral("25", fahrenheit));

        String queryString = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "SELECT * WHERE { " +
            "    ?s rdf:value ?temperature . " +
            "} ORDER BY ?temperature";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet results = qexec.execSelect();
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                System.out.println(soln);
            }
        } finally {
            qexec.close();
        }
    }

}

abstract class AbstractTemperatureType extends BaseDatatype {

    public AbstractTemperatureType(String uri) {
        super(uri);
    }

    @Override public abstract Class<?> getJavaClass();    
    @Override public abstract String unparse(Object value); 
    @Override public abstract Object parse(String lexicalForm) throws DatatypeFormatException;
    @Override public abstract String toString();

}

class TemperatureCelsius extends AbstractTemperatureType {

    private static TemperatureCelsius datatype = new TemperatureCelsius();
    public static TemperatureCelsius get() { return datatype; }
    
    public TemperatureCelsius() {
        super("http://jena.apache.org/datatypes/temperature/celsius");
    }

    @Override
    public Class<?> getJavaClass() {
        return Double.class;
    }

    @Override
    public Object parse(String lexicalForm) throws DatatypeFormatException {
        try {
            return new Double(lexicalForm); 
        } catch (NumberFormatException ex) {
            throw new DatatypeFormatException(lexicalForm, this, ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Temperature in °C";
    }

    @Override
    public String unparse(Object value) {
        return value.toString();
    }
    
}

class TemperatureFahrenheit extends AbstractTemperatureType {

    private static TemperatureFahrenheit datatype = new TemperatureFahrenheit();
    public static TemperatureFahrenheit get() { return datatype; }
    public static final double c = 5.0 / 9.0 ;
    
    public TemperatureFahrenheit() {
        super("http://jena.apache.org/datatypes/temperature/fahrenheit");
    }

    @Override
    public Class<?> getJavaClass() {
        return Double.class;
    }

    @Override
    public Object parse(String lexicalForm) throws DatatypeFormatException {
        try {
            return (Double.parseDouble(lexicalForm) - 32.0) * c; 
        } catch (NumberFormatException ex) {
            throw new DatatypeFormatException(lexicalForm, this, ex.getMessage());
        }
    }

    @Override
    public Object cannonicalise( Object value ) {
        return ((Double)value - 32.0) * c;
    }
    
    @Override
    public String toString() {
        return "Temperature in °F";
    }

    @Override
    public String unparse(Object value) {
        return value.toString();
    }
    
}
