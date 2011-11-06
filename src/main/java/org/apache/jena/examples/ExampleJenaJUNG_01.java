package org.apache.jena.examples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.rootdev.jenajung.JenaJungGraph;
import net.rootdev.jenajung.Transformers;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

public class ExampleJenaJUNG_01 {

	// From: https://github.com/shellac/JenaJung/blob/master/src/main/java/net/rootdev/jenajung/examples/ToImage.java
	public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(ExampleJenaJUNG_01.class.getClassLoader());
        Model model = fm.loadModel("data/data.nt");

        Graph<RDFNode, Statement> g = new JenaJungGraph(model);
        Layout<RDFNode, Statement> layout = new FRLayout<RDFNode, Statement>(g);

        int width = 1400;
        int height = 800;

        layout.setSize(new Dimension(width, height));
        VisualizationImageServer<RDFNode, Statement> viz = new VisualizationImageServer<RDFNode, Statement>(layout, new Dimension(width, height));
        RenderContext<RDFNode, Statement> context = viz.getRenderContext();
        context.setEdgeLabelTransformer(Transformers.EDGE);
        context.setVertexLabelTransformer(Transformers.NODE);

        viz.setPreferredSize(new Dimension(width, height));
        Image img = viz.getImage(new Point(width/2, height/2), new Dimension(width, height));

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        // Fill the background in white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        // Draw the image
        g2d.setColor(Color.white);
        g2d.drawImage(img, 0, 0, width, height, Color.blue, null);

        try {
            // Save the image to a file
            ImageIO.write(bi, "PNG", new File("/tmp/graph.png"));
            System.out.println("Image saved");
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
