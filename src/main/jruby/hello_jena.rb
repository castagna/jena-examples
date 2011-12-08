require 'java'

Dir["target/dependency/\*.jar"].each { |jar| require jar }
Dir["target/jena-examples-\*.jar"].each { |jar| require jar }

java_import java.lang.System
java_import org.apache.jena.examples.Utils
java_import com.hp.hpl.jena.rdf.model.ModelFactory

input = Utils.getResourceAsStream("data/data.ttl")
model = ModelFactory.createDefaultModel()
model.read(input, nil, "TURTLE")

puts "\n---- Turtle ----"
model.write(System.out, "TURTLE")
