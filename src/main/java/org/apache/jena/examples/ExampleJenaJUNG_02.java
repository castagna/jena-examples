package org.apache.jena.examples;

import java.awt.Dimension;

import javax.swing.JFrame;

import net.rootdev.jenajung.JenaJungGraph;
import net.rootdev.jenajung.Transformers;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;

public class ExampleJenaJUNG_02 {
	
	// from: https://github.com/shellac/JenaJung
	public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(ExampleJenaJUNG_02.class.getClassLoader());
        Model model = fm.loadModel("data/data.nt");

        Graph<RDFNode, Statement> g = new JenaJungGraph(model);

		Layout<RDFNode, Statement> layout = new FRLayout<RDFNode, Statement>(g);
		layout.setSize(new Dimension(600, 400));
		BasicVisualizationServer<RDFNode, Statement> viz = new BasicVisualizationServer<RDFNode, Statement>(layout);
		RenderContext<RDFNode, Statement> context = viz.getRenderContext();
		context.setEdgeLabelTransformer(Transformers.EDGE); // property label
		context.setVertexLabelTransformer(Transformers.NODE); // node label
		viz.setPreferredSize(new Dimension(600, 400));
		JFrame frame = new JFrame("Jena Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viz);
		frame.pack();
		frame.setVisible(true);

	}
}
