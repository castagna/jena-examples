
import _root_.java.lang.System
import _root_.org.apache.jena.examples.Utils
import _root_.org.apache.jena.rdf.model.ModelFactory

object HelloJena {
    def main(args: Array[String]) {
        val input = Utils.getResourceAsStream("data/data.ttl")

        val model = ModelFactory.createDefaultModel()
        model.read(input, null, "TURTLE")

        System.out.println("\n---- Turtle ----")
        model.write(System.out, "TURTLE")
    }
}
