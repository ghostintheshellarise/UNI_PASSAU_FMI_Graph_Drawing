// =============================================================================
//
//   ZoomChangeComponent.java
//
//   Copyright (c) 2001-2006 Gravisto Team, University of Passau
//
// =============================================================================
// $Id: ZoomChangeComponent.java 5772 2010-05-07 18:47:22Z gleissner $

package org.graffiti.plugins.tools.zoomtool;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;

import org.graffiti.attributes.Attribute;
import org.graffiti.editor.GraffitiInternalFrame;
import org.graffiti.editor.GraffitiSingleton;
import org.graffiti.editor.MainFrame;
import org.graffiti.graph.Edge;
import org.graffiti.graph.Graph;
import org.graffiti.graph.Node;
import org.graffiti.graphics.CoordinateAttribute;
import org.graffiti.graphics.DimensionAttribute;
import org.graffiti.graphics.EdgeGraphicAttribute;
import org.graffiti.graphics.GraphicAttributeConstants;
import org.graffiti.plugin.gui.AbstractGraffitiComponent;
import org.graffiti.plugin.view.View;
import org.graffiti.plugin.view.ViewListener;
import org.graffiti.plugin.view.Zoomable;
import org.graffiti.session.Session;
import org.graffiti.session.SessionListener;
import org.graffiti.util.SpringUtilities;

/**
 * Impements the zoom tool. You can choose a zoom manually by choosing or typing
 * the zoom-percentage. Or you click the magnifier-icon to zoom the graph to the
 * view port.
 */
public class ZoomChangeComponent extends AbstractGraffitiComponent implements
        ActionListener, ViewListener, SessionListener {
    /**
     * 
     */
    private static final long serialVersionUID = -5502856409002066621L;

    /** DOCUMENT ME! */
    private JComboBox combo;

    /** button */
    private JButton zoomToFitButton;

    private final String buttonToolTip = "Adjust zoom to the viewport.";

    /** The active session. */
    private Session activeSession;

    /** The predefined zoom values, choosable in a combo box. */
    private final String[] zoomValues = new String[] { " 10%", " 25%", " 50%",
            " 75%", "100%", "125%", "150%", "175%", "200%" };

    /**
     * Constructor for ZoomChangeComponent.
     * 
     * @param prefComp
     *            The prefComp.
     */
    public ZoomChangeComponent(String prefComp) {
        super(prefComp);
        setLayout(new SpringLayout());

        combo = new JComboBox(zoomValues);
        combo.setSelectedIndex(4);
        combo.setEditable(true);
        combo.addActionListener(this);
        add(combo);

        ImageBundle iBundle = ImageBundle.getInstance();
        ImageIcon icon = iBundle.getImageIcon("toolbar.zoom.fit");
        icon.setImage(icon.getImage().getScaledInstance(16, 16,
                Image.SCALE_SMOOTH));

        zoomToFitButton = new JButton(icon);
        zoomToFitButton.addActionListener(this);
        zoomToFitButton.setToolTipText(buttonToolTip);
        add(zoomToFitButton);

        SpringUtilities.makeCompactGrid(this, 1, 2, 0, 0, 0, 0);
    }

    /*
     * @see javax.swing.JComponent#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(combo.getPreferredSize().width
                + zoomToFitButton.getPreferredSize().width, Math.max(combo
                .getPreferredSize().height,
                zoomToFitButton.getPreferredSize().height));
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(combo)) {
            Object selectedZoom = combo.getSelectedItem();

            String zoomStr;

            if (selectedZoom instanceof AffineTransform) {
                AffineTransform at = (AffineTransform) selectedZoom;
                zoomStr = ((int) ((at.getScaleX() + at.getScaleY()) / 2d) * 100)
                        + "%";
            } else {
                zoomStr = selectedZoom.toString();
            }

            double zoom = 1d;
            try {
                if (zoomStr.indexOf("%") != -1) {
                    // crop "%"
                    zoom = Double.parseDouble(zoomStr.substring(0, zoomStr
                            .length() - 1)) / 100d;
                } else {
                    zoom = Double.parseDouble(zoomStr) / 100d;
                }

                if (activeSession != null) {
                    Zoomable zoomView = activeSession.getActiveView();
                    zoomView.setZoom(zoom);
                }
            } catch (NumberFormatException nfe) {
                return;
            }
        } else if (e.getSource().equals(zoomToFitButton)) {
            moveAndResizeGraph();
        } else {
            System.err.println("Oh Oh, something went terribly wrong...");
        }
    }

    /**
     * @see org.graffiti.session.SessionListener#sessionChanged(org.graffiti.session.Session)
     */
    public void sessionChanged(Session s) {
        activeSession = s;

        if (s != null) {
            viewChanged(s.getActiveView());
        }
    }

    /**
     * @see org.graffiti.session.SessionListener#sessionDataChanged(org.graffiti.session.Session)
     */
    public void sessionDataChanged(Session s) {
        activeSession = s;
        viewChanged(s.getActiveView());
    }

    /**
     * @see org.graffiti.plugin.view.ViewListener#viewChanged(org.graffiti.plugin.view.View)
     */
    public void viewChanged(View newView) {
        Object newZoom = newView.getZoomTransform();
        String zoomStr;

        if (newZoom instanceof AffineTransform) {
            AffineTransform at = (AffineTransform) newZoom;
            zoomStr = (int) (((at.getScaleX() + at.getScaleY()) / 2d) * 100)
                    + "%";
        } else {
            zoomStr = newZoom.toString();
        }

        combo.setSelectedItem(zoomStr);
    }

    /**
     * Moves a graph to the left and top viewport border. Then the graph is
     * zoomed, if necessary, so the whole graph fits into the viewport.
     * 
     */
    public void moveAndResizeGraph() {
        try {
            final MainFrame mf = GraffitiSingleton.getInstance().getMainFrame();
            final Graph graph = mf.getActiveSession().getGraph();

            double minX = Integer.MAX_VALUE;
            double minY = Integer.MAX_VALUE;

            for (Node node : graph.getNodes()) {
                CoordinateAttribute ca = (CoordinateAttribute) node
                        .getAttribute(GraphicAttributeConstants.GRAPHICS
                                + Attribute.SEPARATOR
                                + GraphicAttributeConstants.COORDINATE);

                DimensionAttribute da = (DimensionAttribute) node
                        .getAttribute(GraphicAttributeConstants.GRAPHICS
                                + Attribute.SEPARATOR
                                + GraphicAttributeConstants.DIMENSION);

                // get the minimal sum of a nodes coordinates and it's dimension
                minX = Math.min(ca.getX() - da.getWidth() / 2d, minX);
                minY = Math.min(ca.getY() - da.getHeight() / 2d, minY);
            }

            for (Edge edge : graph.getEdges()) {
                EdgeGraphicAttribute ega = (EdgeGraphicAttribute) edge
                        .getAttribute(GraphicAttributeConstants.GRAPHICS);
                Collection<Attribute> bendsColl = ega.getBends()
                        .getCollection().values();
                for (Attribute attr : bendsColl) {
                    CoordinateAttribute ca = (CoordinateAttribute) attr;
                    minX = Math.min(ca.getX(), minX);
                    minY = Math.min(ca.getY(), minY);
                }
            }

            // the minimal distance to the left and top border
            minX -= 10d;
            minY -= 10d;

            double maxX = Integer.MIN_VALUE;
            double maxY = Integer.MIN_VALUE;

            for (Node node : graph.getNodes()) {
                CoordinateAttribute ca = (CoordinateAttribute) node
                        .getAttribute(GraphicAttributeConstants.GRAPHICS
                                + Attribute.SEPARATOR
                                + GraphicAttributeConstants.COORDINATE);
                DimensionAttribute da = (DimensionAttribute) node
                        .getAttribute(GraphicAttributeConstants.GRAPHICS
                                + Attribute.SEPARATOR
                                + GraphicAttributeConstants.DIMENSION);

                // move the node to the left and top border
                final Point2D p = ca.getCoordinate();
                p.setLocation(ca.getX() - minX, ca.getY() - minY);
                ca.setCoordinate(p);

                // get the maximal sum of a nodes coordinates and it's dimension
                maxX = Math.max(p.getX() + da.getWidth() / 2d, maxX);
                maxY = Math.max(p.getY() + da.getHeight() / 2d, maxY);
            }

            final double bendOffset = 10d;

            for (Edge edge : graph.getEdges()) {
                EdgeGraphicAttribute ega = (EdgeGraphicAttribute) edge
                        .getAttribute(GraphicAttributeConstants.GRAPHICS);
                Collection<Attribute> bendsColl = ega.getBends()
                        .getCollection().values();
                for (Attribute attr : bendsColl) {
                    CoordinateAttribute ca = (CoordinateAttribute) attr;

                    // move the node to the left and top border
                    final Point2D p = ca.getCoordinate();
                    p.setLocation(ca.getX() - minX, ca.getY() - minY);
                    ca.setCoordinate(p);
                    maxX = Math.max(p.getX() + bendOffset, maxX);
                    maxY = Math.max(p.getY() + bendOffset, maxY);
                }
            }

            // zoom the graph to fit into the screen...
            for (GraffitiInternalFrame iFrame : mf.getActiveFrames()) {

                final Session ses = iFrame.getSession();

                if (mf.getActiveEditorSession() == ses) {

                    double frameWidth = iFrame.getWidth();
                    double frameHeight = iFrame.getHeight();

                    double zoom = 1.0;

                    // determine the zoom caused by a horizontal viewport
                    // transgression
                    if (maxX > frameWidth) {
                        zoom = frameWidth / maxX;
                    }

                    // determine the zoom caused by a vertical viewport
                    // transgression
                    if (maxY > frameHeight)
                        if ((frameHeight / maxY) < zoom) {
                            zoom = frameHeight / maxY;
                        }

                    // if the zoom changed, increase the zoom because of the
                    // viewport's scroll bars, which are not considered in the
                    // frame width
                    if (zoom != 1.0) {
                        zoom *= 0.95;
                    }

                    Session activeSession = mf.getActiveSession();
                    Zoomable zoomView = activeSession.getActiveView();
                    zoomView.setZoom(zoom);
                    this.viewChanged(activeSession.getActiveView());
                }
            }
        } catch (NullPointerException nlp) {
            // no view or no graph found -> do nothing
        }
    }
}

// -----------------------------------------------------------------------------
// end of file
// -----------------------------------------------------------------------------