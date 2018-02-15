// =============================================================================
//
//   HVLayoutForBinTrees.java
//
//   Copyright (c) 2001-2006, Gravisto Team, University of Passau
//
// =============================================================================
// $Id: TipoverLayout.java 5766 2010-05-07 18:39:06Z gleissner $

package org.graffiti.plugins.algorithms.treedrawings.subtreeSeperation.tipover;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.graffiti.attributes.Attribute;
import org.graffiti.editor.GraffitiSingleton;
import org.graffiti.graph.Node;
import org.graffiti.graphics.CoordinateAttribute;
import org.graffiti.graphics.GraphicAttributeConstants;
import org.graffiti.plugin.algorithm.AbstractAlgorithm;
import org.graffiti.plugin.algorithm.PreconditionException;
import org.graffiti.plugin.algorithm.PreconditionException.Entry;
import org.graffiti.plugin.parameter.DoubleParameter;
import org.graffiti.plugin.parameter.Parameter;
import org.graffiti.plugin.parameter.SelectionParameter;
import org.graffiti.plugin.parameter.StringSelectionParameter;
import org.graffiti.plugins.algorithms.treedrawings.GraphChecker;
import org.graffiti.plugins.algorithms.treedrawings.subtreeSeperation.AtomFinderTipover;
import org.graffiti.plugins.algorithms.treedrawings.subtreeSeperation.CostFunction;
import org.graffiti.plugins.algorithms.treedrawings.subtreeSeperation.LayoutComposition;
import org.graffiti.plugins.algorithms.treedrawings.subtreeSeperation.LayoutConstants;
import org.graffiti.plugins.algorithms.treedrawings.subtreeSeperation.LayoutRefresher;
import org.graffiti.selection.Selection;

/**
 * This is the algorithm used for laying out trees using an Tipover-Layout
 * 
 * @author Andreas
 * @version $Revision: 5766 $ $Date: 2010-05-07 19:21:40 +0200 (Fr, 07 Mai 2010)
 *          $
 */
public class TipoverLayout extends AbstractAlgorithm {

    /**
     * What to do with the selected Node
     */
    private int selectedNodePolicy;

    /**
     * In this <code>selectedNodePolicy</code> the selected Node is ignored and
     * the graph is laid out as a whole.
     */
    public static final int NONE_TAKE_ALL = 0;

    /**
     * In this <code>selectedNodePolicy</code> only the fixed drawings of the
     * sutrees of the children of the selected Node can be rearranged.
     */
    public static final int ONLY_LOCAL = 1;

    /**
     * In this <code>selectedNodePolicy</code> only the subtree of the selected
     * Node is laid out using a h-v-Layout.
     */
    public static final int DOWNWARDS = 2;

    /** The root of the tree. */
    public Node root = null;

    /** Selection */
    private Selection selection;

    /**
     * The distance between a Node and its children and also the distance
     * between the subtrees of the children.
     */
    private double nodeDistance;

    /**
     * The name of the type of the cost function that is used
     */
    protected String costFunctionName;

    /**
     * This determines whether this layout is generated using the dimensions of
     * the Nodes or if they should be reduced to points.
     */
    protected boolean nodesWithDimensions;

    /**
     * The additional variable which is used for the CostFunction
     * <code>costFunction</code>
     */
    public double additionalVariable;

    /**
     * The parameter for the node dimension (see
     * <code>nodesWithDimensions</code>)
     */
    private StringSelectionParameter nodeDimensionParameter;

    /**
     * The parameter for the node distance (see <code>nodeDistance</code>)
     */
    private DoubleParameter nodeDistanceParameter;

    /**
     * The name of the type of the cost function (see
     * <code>costFunctionName</code>)
     */
    private StringSelectionParameter costFunctionParameter;

    /**
     * The costFunction used in this TipoverLayout. It is used to determine the
     * optimum h-v-drawing out of the drawings that are generated by the
     * AtomFinder used (depending on the user selection, see
     * <code>selectedAtomFinderStrategy</code>).
     */
    private CostFunction costFunction;

    /**
     * The parameter for the additional variable (see
     * <code>additionalVariable</code>)
     */
    private DoubleParameter additionalVariableParameter;

    /**
     * The selected node. What to do with it is determined by
     * <code>selectedNodePolicy</code>
     */
    private Node selectedNode;

    /**
     * The LayoutRefresher used in this TipoverLayout.
     */
    private LayoutRefresher layoutRefresher;

    /**
     * The reconstructed LayoutCompositions of the subtrees of the children of
     * <code>selected</code>, if <code>selectedNodePolicy</code> is
     * <code>ONLY_LOCAL</code>.
     */
    private List<LayoutComposition> reconstructedCompositions;

    /**
     * The parameter for <code>selectedNodePolicy</code>
     */
    private StringSelectionParameter selectedNodePolicyParameter;

    /**
     * set the <code>nodeDistance</code>
     * 
     * @param nodeDistance
     */
    public void setNodeDistance(double nodeDistance) {
        this.nodeDistance = nodeDistance;
    }

    /**
     * set the variable <code>costFunctionName</code>
     * 
     * @param costFunctionName
     */
    public void setCostFunctionName(String costFunctionName) {
        this.costFunctionName = costFunctionName;
    }

    /**
     * Set the policy for the selected node.
     * 
     * @param selectedNodePolicy
     */
    public void setSelectedNodePolicy(int selectedNodePolicy) {
        this.selectedNodePolicy = selectedNodePolicy;
    }

    /**
     * Set the selected Node (for non-GUI use).
     * 
     * @param selectedNode
     */
    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
    }

    /**
     * Set the variable nodesWithDimensions (for non-GUI use).
     * 
     * @param nodesWithDimensions
     */
    public void setNodesWithDimensions(boolean nodesWithDimensions) {
        this.nodesWithDimensions = nodesWithDimensions;
    }

    /**
     * Set the variable <code>additionalVariable</code> (for non-GUI use).
     * 
     * @param additionalVariable
     */
    public void setAdditionalVariable(double additionalVariable) {
        this.additionalVariable = additionalVariable;
    }

    public TipoverLayout() {
        String[] nodeDimensionParameterNames = new String[] { "POINT_NODES",
                "NODES_WITH_DIMENSIONS" };
        this.nodeDimensionParameter = new StringSelectionParameter(
                nodeDimensionParameterNames, "Node Dimension", "...");

        this.selectedNodePolicyParameter = new StringSelectionParameter(
                new String[] { "NONE_TAKE_ALL", "DOWNWARDS", "ONLY_LOCAL" },
                "Selected Node Policy", "Only if Nodes selected");

        this.nodeDistanceParameter = new DoubleParameter(new Double(50),
                "Distance Between Nodes:", "...");

        this.costFunctionParameter = new StringSelectionParameter(
                CostFunction.costFunctionNames, "Cost Function", "..");

        this.additionalVariableParameter = new DoubleParameter(new Double(200),
                "Constraint or Aspect Ratio",
                "(needed for some costfunctions)", new Double(0), new Double(
                        1000));
    }

    /**
     * @see org.graffiti.plugin.algorithm.Algorithm#getName()
     */
    public String getName() {
        return "TipoverLayout Algorithm";
    }

    @Override
    public Parameter<?>[] getAlgorithmParameters() {

        SelectionParameter seleParam = new SelectionParameter("Root:",
                "Root of this tree.");
        return new Parameter[] { seleParam, this.nodeDimensionParameter,
                this.selectedNodePolicyParameter, this.nodeDistanceParameter,
                this.costFunctionParameter, this.additionalVariableParameter };

    }

    /**
     * @see org.graffiti.plugin.AbstractParametrizable#setAlgorithmParameters(Parameter[])
     */
    @Override
    public void setAlgorithmParameters(Parameter<?>[] params) {
        this.selection = ((SelectionParameter) params[0]).getSelection();

        String nodeDimensionName = ((StringSelectionParameter) params[1])
                .getValue();

        if (nodeDimensionName.equals("POINT_NODES")) {
            this.nodesWithDimensions = false;
        } else if (nodeDimensionName.equals("NODES_WITH_DIMENSIONS")) {
            this.nodesWithDimensions = true;
        }

        String selectedNodePolicyName = ((StringSelectionParameter) params[2])
                .getValue();
        if (selectedNodePolicyName.equals("NONE_TAKE_ALL")) {
            this.selectedNodePolicy = TipoverLayout.NONE_TAKE_ALL;
        } else if (selectedNodePolicyName.equals("ONLY_LOCAL")) {
            this.selectedNodePolicy = TipoverLayout.ONLY_LOCAL;
        } else if (selectedNodePolicyName.equals("DOWNWARDS")) {
            this.selectedNodePolicy = TipoverLayout.DOWNWARDS;
        }

        this.nodeDistance = ((DoubleParameter) params[3]).getDouble();

        this.costFunctionName = ((StringSelectionParameter) params[4])
                .getSelectedValue();

        this.additionalVariable = ((DoubleParameter) params[5]).getDouble();
    }

    /**
     * Check the following:<BR>
     * <BR>
     * 1. Is the given Graph a tree.<BR>
     * 2. If <code>selectedNodePolicy</code> is <code>ONLY_LOCAL</code>: Do all
     * necessary layout-Attributes exist in the subtrees of the children of
     * <code>selectedNode</code>.
     * 
     * @see org.graffiti.plugin.algorithm.Algorithm#check()
     */
    @Override
    public void check() throws PreconditionException {

        this.layoutRefresher = new LayoutRefresher();

        try {
            this.root = GraphChecker.checkTree(this.graph, Integer.MAX_VALUE);

            if (this.selectedNodePolicy == TipoverLayout.DOWNWARDS
                    || this.selectedNodePolicy == TipoverLayout.ONLY_LOCAL) {
                if (this.selection.getNodes().size() != 1)
                    throw new PreconditionException(
                            "Please select exactly one Node.");
                this.selectedNode = this.selection.getNodes().get(0);

                if (this.selectedNodePolicy == TipoverLayout.ONLY_LOCAL) {
                    this.reconstructedCompositions = new LinkedList<LayoutComposition>();

                    for (Node currentChildNode : this.selectedNode
                            .getAllOutNeighbors()) {
                        this.reconstructedCompositions.add(this.layoutRefresher
                                .reconstructComposition(currentChildNode));
                    }
                }
            }
        } catch (PreconditionException p) {
            if (this.selection != null) {
                this.selection.clear();
            }

            Iterator<Entry> itr = p.iterator();
            while (itr.hasNext()) {
                Selection selection = (Selection) itr.next().source;
                if (selection != null) {
                    if (this.selection == null) {
                        this.selection = new Selection();
                    }
                    this.selection.addSelection(selection);
                }
            }
            throw p;
        }

    }

    /*
     * Create a Tipover-Layout using the parameters and modes described above.
     * 
     * @see org.graffiti.plugin.algorithm.Algorithm#execute()
     */
    public void execute() {

        this.graph.getListenerManager().transactionStarted(this);

        LayoutConstants.setMinimumHeightAndWidth(this.graph);

        /**
         * create the TipoverComposition factory. Only a dummy TipoverCompsotion
         * object is needed here to initialize the factory.
         */

        long startTime = System.currentTimeMillis();

        TipoverComposition compositionFactory = new TipoverComposition();

        AtomFinderTipover atomFinder = new AtomFinderTipover(
                compositionFactory, this.nodeDistance, this.nodesWithDimensions);

        List<LayoutComposition> atoms = null;

        if (this.selectedNodePolicy == TipoverLayout.NONE_TAKE_ALL) {
            atoms = atomFinder.findAtoms(this.root);
        } else if (this.selectedNodePolicy == TipoverLayout.DOWNWARDS) {
            atoms = atomFinder.findAtoms(this.selectedNode);
        } else if (this.selectedNodePolicy == TipoverLayout.ONLY_LOCAL) {
            List<LayoutComposition> horizontalComposition = compositionFactory
                    .instance(this.selectedNode,
                            this.reconstructedCompositions, true,
                            this.nodeDistance, this.nodesWithDimensions);
            List<LayoutComposition> verticalComposition = compositionFactory
                    .instance(this.selectedNode,
                            this.reconstructedCompositions, false,
                            this.nodeDistance, this.nodesWithDimensions);

            atoms = new LinkedList<LayoutComposition>();
            atoms.addAll(horizontalComposition);
            atoms.addAll(verticalComposition);
        } else
            throw new IllegalStateException("Unknown Selected Node policy.");

        this.costFunction = new CostFunction(this.costFunctionName,
                this.additionalVariable);

        LayoutComposition bestFound = null;

        try {
            bestFound = costFunction.findBestAtom(atoms);
        } catch (IllegalStateException i) {
            throw i;
        }

        if (bestFound != null) {
            long timeNeeded = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();

            bestFound.setLayoutAttributes(true);

            String warningMessage = "";

            // try if we can refresh the whole graph...
            try {
                LayoutComposition wholeGraphComposition = this.layoutRefresher
                        .reconstructComposition(this.root);
                wholeGraphComposition.layout(new Point2D.Double(0, 0));
            } catch (PreconditionException p) {
                warningMessage = "WARNING: Could not layout the whole graph (only the subtree). Use LayoutRefresher!";

                try {
                    LayoutComposition subtreeComposition = this.layoutRefresher
                            .reconstructComposition(this.selectedNode);

                    CoordinateAttribute ca = (CoordinateAttribute) bestFound
                            .getRoot()
                            .getAttribute(
                                    GraphicAttributeConstants.GRAPHICS
                                            + Attribute.SEPARATOR
                                            + GraphicAttributeConstants.COORDINATE);

                    subtreeComposition.layout(ca.getCoordinate());
                } catch (PreconditionException e) {
                    // This could happen if nodes are copied with their
                    // orderNumbers. Don't do that!
                    e.printStackTrace();
                }
            }

            long timeNeededForLayout = System.currentTimeMillis() - startTime;

            String message = warningMessage + "Width: " + bestFound.getWidth()
                    + ", Height: " + bestFound.getHeight()
                    + ", value of costfunction: "
                    + this.costFunction.costOf(bestFound)
                    + ", Tipover-Layout found after: " + timeNeeded + "ms, "
                    + "Time needed for layouting GraphElements: "
                    + timeNeededForLayout + "ms";

            try {
                GraffitiSingleton.getInstance().getMainFrame().getStatusBar()
                        .showInfo(message, 60000);
            } catch (NullPointerException n) {
                // Never mind
            }

            System.out.println(message);
            // System.out.println("Selected Atom:\n" +
            // bestFound.showStructure());
        }
        this.graph.getListenerManager().transactionFinished(this);
    }
}

// -----------------------------------------------------------------------------
// end of file
// -----------------------------------------------------------------------------
