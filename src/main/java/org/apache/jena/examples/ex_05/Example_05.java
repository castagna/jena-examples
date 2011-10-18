package org.apache.jena.examples.ex_05;

import java.io.InputStream;

import org.apache.jena.examples.ex_01.Example_01;
import org.openjena.riot.Lang;
import org.openjena.riot.RiotReader;
import org.openjena.riot.RiotWriter;
import org.openjena.riot.lang.SinkTriplesToGraph;

import com.hp.hpl.jena.graph.Factory;
import com.hp.hpl.jena.graph.Graph;

public class Example_05 {

    public static void main(String[] args) {
        InputStream in = Example_01.getResourceAsStream(Example_01.class);
        Graph graph = Factory.createGraphMem();
        RiotReader.parseTriples(in, Lang.TURTLE, null, new SinkTriplesToGraph(graph));
        RiotWriter.writeTriples(System.out, graph);
    }

}
