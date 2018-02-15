// =============================================================================
//
//   RandomTournamentGraphGenerator.java
//
//   Copyright (c) 2001-2006, Gravisto Team, University of Passau
//
// =============================================================================
// $Id: RandomTournamentGraphGenerator.java 5766 2010-05-07 18:39:06Z gleissner $

package org.graffiti.plugins.algorithms.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.graffiti.graph.Edge;
import org.graffiti.graph.Node;
import org.graffiti.plugin.algorithm.PreconditionException;
import org.graffiti.plugin.parameter.IntegerParameter;

/**
 * @author Marek Piorkowski
 * @version $Revision: 5766 $ $Date: 2006-04-05 23:01:53 +0200 (Mi, 05 Apr 2006)
 *          $
 */
public class RandomTournamentGraphGenerator extends AbstractGenerator {
    /** The number of nodes. */
    private IntegerParameter nodesParam;

    public RandomTournamentGraphGenerator() {
        super();
        addNodeLabelingOption();
        addEdgeLabelingOption();
        addFormSelOption();
        nodesParam = new IntegerParameter(new Integer(5), new Integer(0),
                new Integer(100), "number of nodes",
                "the number of nodes to generate");
        parameterList.addFirst(nodesParam);
    }

    /**
     * @see org.graffiti.plugin.algorithm.Algorithm#getName()
     */
    public String getName() {
        return "Random Graph Generator: Tournament";
    }

    /**
     * @see org.graffiti.plugin.algorithm.Algorithm#check()
     */
    @Override
    public void check() throws PreconditionException {
        PreconditionException errors = new PreconditionException();

        if (nodesParam.getValue().compareTo(new Integer(0)) < 0) {
            errors.add("The number of nodes may not be smaller than zero.");
        }

        if (graph == null) {
            errors.add("The graph instance may not be null.");
        }

        if (!errors.isEmpty())
            throw errors;
    }

    /**
     * @see org.graffiti.plugin.algorithm.Algorithm#execute()
     */
    public void execute() {
        int numberOfNodes = nodesParam.getValue().intValue();

        ArrayList<Node> nodes = new ArrayList<Node>(numberOfNodes);
        Collection<Edge> edges = new LinkedList<Edge>();

        graph.getListenerManager().transactionStarted(this);

        for (int i = 0; i < numberOfNodes; ++i) {
            nodes.add(graph.addNode());
            for (int j = 0; j < i; ++j) {

                if (i != j) {
                    double random = Math.random();
                    if (random > 0.5) {
                        edges.add(graph.addEdge(nodes.get(i), nodes.get(j),
                                true));
                    } else {
                        edges.add(graph.addEdge(nodes.get(j), nodes.get(i),
                                true));
                    }
                }
            }
        }

        formGraph(nodes, form.getSelectedValue());

        // label the nodes
        if (nodeLabelParam.getBoolean().booleanValue()) {
            labelNodes(nodes, startNumberParam.getValue().intValue());
        }

        // label the edges
        if (edgeLabelParam.getBoolean().booleanValue()) {
            labelEdges(edges, edgeLabelNameParam.getString(), edgeMin
                    .getValue().intValue(), edgeMax.getValue().intValue());
        }

        // set edge arrows
        setEdgeArrows(graph);

        graph.getListenerManager().transactionFinished(this);
    }

    /**
     * @see org.graffiti.plugin.algorithm.Algorithm#reset()
     */
    @Override
    public void reset() {
        super.reset();
        nodesParam.setValue(new Integer(5));
    }

}

// -----------------------------------------------------------------------------
// end of file
// -----------------------------------------------------------------------------
