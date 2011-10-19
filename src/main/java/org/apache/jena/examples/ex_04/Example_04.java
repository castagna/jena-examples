package org.apache.jena.examples.ex_04;

import java.io.InputStream;

import org.apache.jena.examples.ex_01.Example_01;
import org.openjena.atlas.lib.SinkPrint;
import org.openjena.riot.Lang;
import org.openjena.riot.RiotReader;

import com.hp.hpl.jena.graph.Triple;

public class Example_04 {

    public static void main(String[] args) {
        InputStream in = Example_01.getResourceAsStream(Example_01.class, "data.ttl");
        RiotReader.parseTriples(in, Lang.TURTLE, null, new SinkPrint<Triple>(System.out));
    }

}
