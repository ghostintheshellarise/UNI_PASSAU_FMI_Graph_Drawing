// =============================================================================
//
//   TestOutputSerializer.java
//
//   Copyright (c) 2001-2006 Gravisto Team, University of Passau
//
// =============================================================================
// $Id: TestOutputSerializer.java 5769 2010-05-07 18:42:56Z gleissner $

package de.chris.plugins.outputserializers.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import org.graffiti.graph.Edge;
import org.graffiti.graph.Graph;
import org.graffiti.graph.Node;
import org.graffiti.plugin.io.AbstractOutputSerializer;

/**
 * An implementation of a simple exporter that writes only structural
 * information of the graph in GML format in a file.
 * 
 * @author chris
 */
public class TestOutputSerializer extends AbstractOutputSerializer {

    /** Prepresents a tabulator. */
    private static final String TAB = "  ";

    /**
     * Determines the extension of the written files. This extension occurs in
     * Gravistos File->Save and File->Save as.. Files of type combo box which
     * chooses the serializer which should be used and thus the format of the
     * file.
     * 
     * @see org.graffiti.plugin.io.Serializer#getExtensions()
     */
    public String[] getExtensions() {
        return new String[] { ".cgml" };
    }

    /**
     * Writes the contents of the given graph to a stream.
     * 
     * @param o
     *            The output stream to save the graph to.
     * @param g
     *            The graph to save.
     * 
     * @throws IOException
     *             DOCUMENT ME!
     * 
     * @see org.graffiti.plugin.io.OutputSerializer#write(OutputStream, Graph)
     */
    public void write(OutputStream o, Graph g) throws IOException {
        PrintStream p = new PrintStream(o);

        // write the graph's open tag
        p.println("graph [");

        // write the graph's directed flag
        p.println(createTabs(1) + "directed " + (g.isDirected() ? "1" : "0"));

        // write the nodes
        writeNodes(p, g);

        // write the edges
        writeEdges(p, g);

        // write the graph's close tag
        p.println("]");
        p.close();
    }

    /**
     * Creates and returns TAB + TAB + ... + TAB (level).
     * 
     * @param level
     *            of indentation.
     * 
     * @return a string of <code>level</code> TABs.
     */
    private String createTabs(int level) {
        StringBuffer b = new StringBuffer();

        for (int i = 0; i < level; i++) {
            b.append(TAB);
        }

        return b.toString();
    }

    /**
     * Writes the edge of the given graph to the given print stream.
     * 
     * @param p
     *            the stream to write to.
     * @param g
     *            the graph to get the data from.
     */
    private void writeEdges(PrintStream p, Graph g) {
        int edgeId = 0;

        for (Iterator<Edge> it = g.getEdgesIterator(); it.hasNext();) {
            Edge e = it.next();

            p.println(createTabs(1) + "edge [");

            p.println(createTabs(2) + "id " + edgeId);

            p
                    .println(createTabs(2)
                            + "source "
                            + e
                                    .getSource()
                                    .getInteger(
                                            "de.chris.plugins.outputserializers.test.TempNodeID"));

            p
                    .println(createTabs(2)
                            + "target "
                            + e
                                    .getTarget()
                                    .getInteger(
                                            "de.chris.plugins.outputserializers.test.TempNodeID"));

            p.println(createTabs(1) + "]");
            ++edgeId;
        }

        for (Iterator<Node> it = g.getNodesIterator(); it.hasNext();) {
            Node n = it.next();
            n
                    .removeAttribute("de.chris.plugins.outputserializers.test.TempNodeID");
        }
    }

    /**
     * Writes the nodes of the given graph to the given print stream.
     * 
     * @param p
     *            the stream to write to.
     * @param g
     *            the graph to get the data from.
     */
    private void writeNodes(PrintStream p, Graph g) {
        int nodeId = 0;

        for (Iterator<Node> it = g.getNodesIterator(); it.hasNext();) {
            Node n = it.next();
            n.setInteger("de.chris.plugins.outputserializers.test.TempNodeID",
                    nodeId);
            p.println(createTabs(1) + "node [");

            p.println(createTabs(2) + "id " + nodeId);
            p.println(createTabs(1) + "]");
            ++nodeId;
        }
    }

    /*
     * @see org.graffiti.plugin.Parametrizable#getName()
     */
    @Override
    public String getName() {
        return "TestOutputSerializer";
    }
}

// -----------------------------------------------------------------------------
// end of file
// -----------------------------------------------------------------------------
