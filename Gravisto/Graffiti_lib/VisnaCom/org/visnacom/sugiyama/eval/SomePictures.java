/*==============================================================================
*
*   SomePictures.java
*
*   @author Michael Proepster
*
*==============================================================================
* $Id: SomePictures.java 907 2005-10-19 12:03:31Z raitner $
*/

package org.visnacom.sugiyama.eval;

import java.util.Collections;
import java.util.List;

import org.visnacom.controller.ViewPanel;
import org.visnacom.model.*;
import org.visnacom.sugiyama.SugiyamaDrawingStyle;
import org.visnacom.sugiyama.algorithm.*;
import org.visnacom.sugiyama.model.CompoundLevel;
import org.visnacom.sugiyama.model.DummyNode;
import org.visnacom.sugiyama.model.SugiCompoundGraph;
import org.visnacom.sugiyama.model.SugiNode;
import org.visnacom.sugiyama.test.TestGraph1;
import org.visnacom.sugiyama.test.TestGraphSugiyama;
import org.visnacom.view.DefaultDrawingStyle;
import org.visnacom.view.Geometry;


/**
 * contains some pictures for my thesis.
 */
public class SomePictures {
    //~ Methods ================================================================

    /**
     * DOCUMENT ME!
     *
     * @param args DOCUMENT ME!
     */
    public static void main(String[] args) {
        //        bildtestgraph1();
        //        bildlocalandexterndummy1();
        //        bildlocalandexterndummy2();
        //        bildSugiyama();
        //        bildtestgraph3proxy();
        //        baustellelocalpath();
        //        baustellechild();
        //        baustelleparent();
        //        baustelleexternalpath();
        //        baustelleende();
        //        centerdrawing();
        //        dummyedgecrossing();
        //      definitionDummyknoten1();
        //      definitionDummyknoten2();
        //      definitionDummyknoten3();
        //      definitionDummyknoten4();
        //        derivedgraphzusatzbild();
        //        einfacheKreuzung();
        //        externeKantenGrob();
        //        externKantenGrobversion2();
        //        groupingzweipfade();
        //        groupingdreipfade();
        //        graphNotCompact();
        //        horizontalInDirekt();
        //        horizontalDirekt();
        //        horizontalEdgesimple();
        //        horizontalEdgeInacceptable();
        //        horizontalEdgeAcceptable();
        //        hilfsknotenfuerhorizontal();
        //        hauptBeispiel();
        //        innersegmentcrossing();
        //        intermediateResult();
        //        independentsubgraphs();
        //        inzidenteKanten();
        //        localHierarchy();
        //        localersetzung();
        //        nonproperedge();
        //        nonproperedgelocalpath();
        //        nonproperedgeexternalpath();
        //        nonproperedgeexternalpathcomplicated();
        //        proxynodeproblem2();
        //        problemcontract();
        //        proxynodeproblem3();
        //        problemctract2();
        //        ubogen();
        //        ukantewegenLR();
        //        beispielAmAnfang();
        //        smallViewExpand();
        polylinie();
    }

    /**
     *
     */
    private static void baustellechild() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        Node n11 = s.newLeaf(s.getRoot());
        Node n12 = s.newLeaf(s.getRoot());

        //        Node n13 = s.newLeaf(s.getRoot());
        Node n14 = s.newLeaf(s.getRoot());
        Node n21 = s.newLeaf(s.getRoot());

        //       Node n22 = s.newLeaf(s.getRoot());
        //       Node n23 = s.newLeaf(s.getRoot());
        //       Node n24 = s.newLeaf(s.getRoot());
        Node n31 = s.newLeaf(n21);

        //       Node n32 = s.newLeaf(n22);
        //       Node n33 = s.newLeaf(n23);
        //       Node n34 = s.newLeaf(n24);
        s.newEdge(n0, n21);
        s.newEdge(n11, n31);
        s.newEdge(n12, n31);
        //        s.newEdge(n13, n31);
        s.newEdge(n14, n31);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = ((SugiNode) s.getRoot()).getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(2);
        level.add(0, level.remove(1));
        ((SugiNode) s.getRoot()).getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        s.deleteLeaf(n0);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "baustellechildafter", "pdf");
    }

    /**
     *
     */
    private static void baustelleende() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());

        //        Node n4 = s.newLeaf(s.getRoot());
        Node n5 = s.newLeaf(s.getRoot());

        //      Node n6 = s.newLeaf(n5);
        //      Node n7 = s.newLeaf(n5); 
        //      Node n8 = s.newLeaf(n5);
        //      Node n9 = s.newLeaf(n5);
        s.newEdge(n1, n5);

        Edge e1 = s.newEdge(n2, n5);
        Edge e2 = s.newEdge(n3, n5);

        //        s.newEdge(n4, n5);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "baustelleendeafter", "pdf");
        s.deleteEdge(e1);
        s.deleteEdge(e2);
        DummyPicture.write(s, "baustelleende", "pdf");
    }

    /**
     *
     */
    private static void baustelleexternalpath() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(n1);
        Node n3 = s.newLeaf(n2);
        Node n4 = s.newLeaf(s.getRoot());
        Node n5 = s.newLeaf(n4);
        Node n6 = s.newLeaf(n5);
        Node n7 = s.newLeaf(n5);
        Node n8 = s.newLeaf(n5);
        Node n9 = s.newLeaf(n5);

        //        Node n10 = s.newLeaf(n1);
        //        Node n11 = s.newLeaf(n1);
        Node n12 = s.newLeaf(n2);
        Node n13 = s.newLeaf(n2);
        s.newEdge(n6, n7);
        s.newEdge(n7, n8);
        s.newEdge(n8, n9);
        s.newEdge(n3, n9);
        s.newEdge(n12, n9);
        s.newEdge(n13, n9);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = ((SugiNode) s.getRoot()).getChildrenAtLevel(1);
        level.add(2, level.remove(3));
        //        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(2);
        //        level.add(0,level.remove(1));
        ((SugiNode) s.getRoot()).getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        s.deleteLeaf(n6);
        s.deleteLeaf(n7);
        s.deleteLeaf(n8);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "baustelleexternalpathafter", "pdf");
    }

    /**
     *
     */
    private static void baustellelocalpath() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());

        //        Node n4 = s.newLeaf(s.getRoot());
        Node n5 = s.newLeaf(s.getRoot());
        Node n6 = s.newLeaf(s.getRoot());
        Node n7 = s.newLeaf(s.getRoot());

        //        Node n8 = s.newLeaf(s.getRoot());
        Node n9 = s.newLeaf(s.getRoot());
        Node n10 = s.newLeaf(s.getRoot());
        Node n11 = s.newLeaf(s.getRoot());

        //        Node n12 = s.newLeaf(s.getRoot());
        s.newEdge(n1, n5);
        s.newEdge(n2, n6);
        s.newEdge(n3, n7);
        //        s.newEdge(n4, n8);
        s.newEdge(n5, n9);
        s.newEdge(n6, n10);
        s.newEdge(n7, n11);
        //        s.newEdge(n8, n12);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "baustellelocalpath", "pdf");
    }

    /**
     *
     */
    private static void baustelleparent() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());

        Node n3 = s.newLeaf(s.getRoot());
        Node n5 = s.newLeaf(s.getRoot());
        Node n6 = s.newLeaf(s.getRoot());
        Node n7 = s.newLeaf(n1);
        Node n9 = s.newLeaf(n1);
        Node n10 = s.newLeaf(n1);

        //        Node n11 = s.newLeaf(n7);
        //        Node n13 = s.newLeaf(n9);
        //        Node n14 = s.newLeaf(n10);
        //        Node n15 = s.newLeaf(n7);
        //        Node n16 = s.newLeaf(n8);
        //        Node n17 = s.newLeaf(n9);
        //        Node n18 = s.newLeaf(n10);
        //      s.newEdge(n1,n);
        s.newEdge(n7, n3);
        s.newEdge(n9, n5);
        s.newEdge(n10, n6);

        //        s.newEdge(n11, n15);
        //        s.newEdge(n13, n17);
        //        s.newEdge(n14, n18);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = ((SugiNode) s.getRoot()).getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        ((SugiNode) s.getRoot()).getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "baustelleparentafter", "pdf");
    }

    /**
     *
     */
    private static void beispielAmAnfang() {
        ViewPanel panel = new ViewPanel();
        Geometry geo = panel.getGeometry();
        View view = geo.getView();
        Node root = view.getRoot();
        Node n1 = root;
        Node n6 = view.newLeaf(n1);
        Node n7 = view.newLeaf(n1);
        Node n2 = view.newLeaf(n1); //wenn 2 und 7 vertauscht, drehen sich

        //            Node n3 = view.newLeaf(n2);
        //            Node n4 = view.newLeaf(n2);
        Node n5 = view.newLeaf(n2);
        Node n8 = view.newLeaf(n6);
        Node n9 = view.newLeaf(n6);

        //            Node n10 = view.newLeaf(n6);
        Node n11 = view.newLeaf(n6);
        Node n12 = view.newLeaf(n7);
        Node n13 = view.newLeaf(n7);
        Node n14 = view.newLeaf(n12);
        Node n15 = view.newLeaf(n12);
        Node n16 = view.newLeaf(n12);

        //            view.newEdge(n3,n5);
        //            view.newEdge(n3,n4);
        view.newEdge(n2, n8);
        //            view.newEdge(n4,n5);
        //            view.newEdge(n4,n9);
        //            view.newEdge(n7,n9);
        view.newEdge(n5, n8);
        view.newEdge(n8, n9);
        //            view.newEdge(n8,n10);
        //            view.newEdge(n10,n11);
        view.newEdge(n11, n9);
        view.newEdge(n12, n9);
        view.newEdge(n13, n9);
        view.newEdge(n14, n16);
        view.newEdge(n14, n15);
        view.newEdge(n15, n13);
        view.newEdge(n15, n16);
        DummyPicture.showIds = false;
        ((SugiyamaDrawingStyle) geo.getDrawingStyle()).setDrawingStyle(
            SugiyamaDrawingStyle.FINAL_STYLE);
        geo.redraw();
        DummyPicture.show(geo);
        DummyPicture.write(geo, "compound_generalSugi", "pdf");

        geo.setDrawingStyle(new DefaultDrawingStyle(geo));
        view.contract(n12);

        view.contract(n7);
        view.contract(n2);
        geo.setDrawingStyle(
            new SugiyamaDrawingStyle(geo, SugiyamaDrawingStyle.FINAL_STYLE));
        DummyPicture.show(geo);
        DummyPicture.write(geo, "compound_generalSugiView", "pdf");
        //            geo.setDrawingStyle(new DefaultDrawingStyle(geo));
        //            view.contract(n6);
        //            geo.setDrawingStyle(new SugiyamaDrawingStyle(geo,
        //                    SugiyamaDrawingStyle.FINAL_STYLE));
        //            DummyPicture.show(geo);
        view.expand(n2);
        DummyPicture.show(geo);
        DummyPicture.write(geo, "compound_generalSugiViewLeftExpanded", "pdf");
        view.contract(n2);
        view.expand(n7);
        DummyPicture.show(geo);
        //            view.expand(n12);
        DummyPicture.write(geo, "compound_generalSugiViewRightExpanded", "pdf");
    }

    /**
     *
     */

    //    private static void bild2() {
    //        Geometry geo = new Geometry();
    //        CompoundGraph c = geo.getView();
    //        //        SugiCompoundGraph s = new SugiCompoundGraph();
    //        Node n1 = c.newLeaf(c.getRoot());
    //        Node n2 = c.newLeaf(c.getRoot());
    //        Node n3 = c.newLeaf(n1);
    //        Node n4 = c.newLeaf(n2);
    //        Node n5 = c.newLeaf(n1);
    //        Node n6 = c.newLeaf(n3);
    //        c.newEdge(n6, n4);
    //        c.newEdge(n6, n5);
    //        SugiyamaDrawingStyle sds = new SugiyamaDrawingStyle(geo);
    //        sds.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
    //        
    //        
    ////        c.setDrawingStyle(SugiyamaDrawingStyle.FINAL_STYLE);
    ////        Hierarchization.hierarchize(c);
    ////        Normalization.normalize(c);
    ////        VertexOrdering.order(c, 2);
    ////        MetricLayout.layout(c);
    ////        DummyPicture.show(c);
    //        //       DummyPicture.write(s,"bild2","png");
    ////        Profiler.reset(false);
    //        sds.draw(geo);
    //        sds.show();
    ////        Evaluation.computeCrossingsAndCuts(geo);
    //    }

    /* node is drawn centered, but should be to the right */

    //    private static void bild4() {
    //        SugiCompoundGraph s = new SugiCompoundGraph();
    //        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
    //        SugiNode n2 = (SugiNode) s.newLeaf(s.getRoot());
    //        SugiNode n3 = (SugiNode) s.newLeaf(n1);
    //        SugiNode n4 = (SugiNode) s.newLeaf(n1);
    //        SugiNode n5 = (SugiNode) s.newLeaf(n1);
    //        SugiNode n6 = (SugiNode) s.newLeaf(n2);
    //        s.newEdge(n5, n6);
    //        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
    //        Hierarchization.hierarchize(s);
    //        n5.setClev(n1.getClev().getSubLevel(2));
    //        n6.setClev(n2.getClev().getSubLevel(3));
    //
    //        Normalization.normalize(s);
    //        VertexOrdering.order(s, 0);
    //        MetricLayout.layout(s);
    //
    //        DummyPicture.show(s);
    //        //       DummyPicture.write(s,"bild4","png");
    //    }

    /**
     *
     */
    private static void bildSugiyama() {
        ViewPanel panel = new ViewPanel();
        Geometry geo = panel.getGeometry();
        TestGraphSugiyama.initialize(geo.getView());
        ((SugiyamaDrawingStyle) geo.getDrawingStyle()).setDrawingStyle(
            SugiyamaDrawingStyle.FINAL_STYLE);
        geo.redraw();
        DummyPicture.show(geo);
        DummyPicture.write(geo, "sugiyamaGraph", "pdf");

        SugiCompoundGraph s = new SugiCompoundGraph();

        //        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
        s.setDrawingStyle(SugiyamaDrawingStyle.FINAL_STYLE);
        TestGraphSugiyama.initialize(s);

        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);

        DummyPicture.show(s);
    }

    /**
     *
     */
    private static void bildlocalandexterndummy1() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n2);
        Node n5 = s.newLeaf(n1);

        s.newEdge(n3, n4);
        s.newEdge(n3, n5);
        //     
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s, 0);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        //       DummyPicture.write(s, "localandexterndummy1", "png");
    }

    /**
     *
     */
    private static void bildlocalandexterndummy2() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n2);
        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(n3);
        Node n7 = s.newLeaf(n2);
        Node n8 = s.newLeaf(n2);

        s.newEdge(n6, n4);
        s.newEdge(n6, n5);
        s.newEdge(n7, n8);
        s.newEdge(n8, n4);
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        //               DummyPicture.write(s, "localandexterndummy2","png");
    }

    /**
     *
     */
    private static void bildtestgraph1() {
        TestGraph1 tg = new TestGraph1();

        ViewPanel panel = new ViewPanel();
        SugiyamaDrawingStyle sds =
            (SugiyamaDrawingStyle) panel.getGeometry().getDrawingStyle();
        tg.fillCompoundGraph(panel.getGeometry().getView());
        sds.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
        panel.getGeometry().redraw();

        DummyPicture.show(sds.s);
        //       DummyPicture.show(panel.getGeometry());
        //       sds.write("testgraph1","pdf");
    }

    /**
     *
     */
    private static void bildtestgraph3proxy() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(n1);

        //       SugiNode n3 = (SugiNode) s.newLeaf(n1);
        //       SugiNode n4 = (SugiNode) s.newLeaf(n1);
        //       SugiNode n5 = (SugiNode) s.newLeaf(n2);
        SugiNode n6 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n7 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n8 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n9 = (SugiNode) s.newLeaf(n7);
        SugiNode n10 = (SugiNode) s.newLeaf(n7);
        SugiNode n11 = (SugiNode) s.newLeaf(n8);
        SugiNode n12 = (SugiNode) s.newLeaf(n8);
        SugiNode n13 = (SugiNode) s.newLeaf(n6);
        SugiNode n14 = (SugiNode) s.newLeaf(n6);
        s.newEdge(n1, n7);

        Edge e2 = s.newEdge(n2, n10);

        //       Edge e3 = s.newEdge(n2, n13);
        Edge e6 = s.newEdge(n10, n11);
        Edge e7 = s.newEdge(n12, n9);
        s.newEdge(n13, n9);
        s.newEdge(n10, n14);
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level1 = s.getMetricRoot().getChildrenAtLevel(1);
        List level2 = s.getMetricRoot().getChildrenAtLevel(2);

        //       level1.add(0,level1.remove(2));
        //        Collections.swap(level2, 2, 3);
        //        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);

        //DummyPicture.write(s, "testgraph3proxy","pdf");
        //       Collections.swap(level2,0,1);
        //        Collections.swap(level2, 2, 3);
        //        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);

        //DummyPicture.write(s, "testgraph3proxywished","pdf");
    }

    /**
     *
     */
    private static void centerdrawing() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n2);
        Node n5 = s.newLeaf(n2);
        Node n6 = s.newLeaf(n2);
        s.newEdge(n3, n6);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);

        //DummyPicture.write(s, "centerdrawing","pdf");
    }

    /**
     *
     */
    private static void definitionDummyknoten1() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(n1);
        SugiNode n3 = (SugiNode) s.newLeaf(n1);
        CompoundLevel rootClev = CompoundLevel.getClevForRoot();
        s.getMetricRoot().setClev(rootClev);
        n1.setClev(rootClev.getSubLevel(1));
        //        n2.setClev(rootClev.getSubLevel(1));
        n2.setClev(n1.getClev().getSubLevel(1));
        n3.setClev(n1.getClev().getSubLevel(3));
        s.newEdge(n2, n3);
        DummyPicture.showIds = false;
        //        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "definitionDummyknotenordinary", "pdf");
    }

    /**
     *
     */
    private static void definitionDummyknoten2() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n0 = (SugiNode) s.newLeaf(s.getRoot());
        Node n1 = s.newLeaf(n0);
        Node n2 = s.newLeaf(n0);
        Node n3 = s.newLeaf(n1);

        //             Node n4 = s.newLeaf(n1);
        Node n2_1 = s.newLeaf(n2);
        Node n2_2 = s.newLeaf(n2);
        Node n2_3 = s.newLeaf(n2);
        Node n2_4 = s.newLeaf(n2);
        s.newEdge(n2_1, n2_2);
        s.newEdge(n2_2, n2_3);
        s.newEdge(n2_3, n2_4);
        s.newEdge(n3, n2_4);

        //        Node n9 = s.newLeaf(s.getRoot());
        //        Node n99 = s.newLeaf(s.getRoot());
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        //             MetricLayout.layout(s);
        //             DummyPicture.show(s);
        //DummyPicture.write(s, "ubogen1","pdf");
        //        List level = s.getMetricRoot().getChildrenAtLevel(1);
        //        level.remove(n9);
        //        level.remove(n99);
        //        level.add(1, n9);
        //        level.add(2, n99);
        //        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        s.deleteLeaf(n2_3);
        s.deleteLeaf(n2_2);
        s.deleteLeaf(n2_1);
        DummyPicture.show(s);
        //        DummyPicture.write(s, "definitionDummyknotenhorizontal","pdf");
        DummyPicture.write(s, n0, "horizontalersetzungVor", "pdf");
    }

    /**
     *
     */
    private static void definitionDummyknoten3() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        Node n1 = s.newLeaf(n0);
        Node n2 = s.newLeaf(n1);
        Node n3 = s.newLeaf(n0);
        s.newEdge(n2, n3);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "definitionDummyknotenlocal", "pdf");
    }

    /**
     *
     */
    private static void definitionDummyknoten4() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n3);
        Node n5 = s.newLeaf(n2);
        s.newEdge(n4, n5);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "definitionDummyknotenexternal", "pdf");
    }

    /**
     *
     */
    private static void derivedgraphzusatzbild() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(n1);
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n2);
        Node n5 = s.newLeaf(n3);
        Node n6 = s.newLeaf(n4);
        Node n7 = s.newLeaf(n5);
        s.newEdge(n6, n7);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);

        //       DummyPicture.write(s, "derivedgraphzusatzbild1","pdf");
        s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);
        n1 = s.newLeaf(s.getRoot());
        n2 = s.newLeaf(n1);
        n3 = s.newLeaf(n1);
        n4 = s.newLeaf(n2);
        n5 = s.newLeaf(n3);
        n6 = s.newLeaf(n4);
        n7 = s.newLeaf(n5);
        //       s.newEdge(n6, n7);
        Hierarchization.hierarchize(s);
        ((SugiNode) n3).setClev(((SugiNode) n1).getClev().getSubLevel(2));
        ((SugiNode) n5).setClev(((SugiNode) n3).getClev().getSubLevel(1));
        ((SugiNode) n7).setClev(((SugiNode) n5).getClev().getSubLevel(1));
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        //       DummyPicture.write(s, "derivedgraphzusatzbild2","pdf");
    }

    /**
     *
     */
    private static void dummyedgecrossing() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n2);
        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(s.getRoot());
        s.newEdge(n3, n4);
        s.newEdge(n3, n5);
        s.newEdge(n5, n6);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        //          Node n5 = s.newDummyLeaf((SugiNode) s.getRoot(), 1, 1, DummyNode.UNKNOWN);
        //         s.newDummyLeaf((SugiNode) n5,1,DummyNode.UNKNOWN);
        MetricLayout.layout(s);
        DummyPicture.show(s);

        // DummyPicture.write(s, "dummyedgecrossing","pdf");
    }

    /**
     *
     */
    private static void einfacheKreuzung() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(n1);
        Node n9 = s.newLeaf(n1);
        Node n10 = s.newLeaf(n1);
        Node n11 = s.newLeaf(n1);
        Node n7 = s.newLeaf(n2);
        Node n8 = s.newLeaf(n2);

        s.newEdge(n7, n3);
        s.newEdge(n7, n4);
        s.newEdge(n7, n8);
        s.newEdge(n8, n5);
        s.newEdge(n8, n6);
        s.newEdge(n3, n6);
        s.newEdge(n4, n5);
        s.newEdge(n9, n10);
        s.newEdge(n10, n11);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = n1.getChildrenAtLevel(2);
        level.add(1, level.remove(2));
        n1.getLocalHierarchy().updatePositions();

        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.write(s, "einfacheKreuzung1", "pdf");
        s.deleteLeaf(n7);
        s.deleteLeaf(n8);
        s.deleteLeaf(n2);
        MetricLayout.layout(s);
        s.deleteLeaf(n3);
        s.deleteLeaf(n4);
        s.deleteLeaf(n5);
        s.deleteLeaf(n6);
        DummyPicture.show(s);
        DummyPicture.write(s, "einfacheKreuzung2", "pdf");
    }

    /**
     *
     */
    private static void externKantenGrobversion2() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        SugiNode n4 = (SugiNode) s.newLeaf(n1);
        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(n2);
        Node n7 = s.newLeaf(n2);

        s.newEdge(n3, n4);
        s.newEdge(n3, n5);
        s.newEdge(n4, n6);
        s.newEdge(n5, n7);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.write(s, "externKantenGrob1", "pdf");

        //
        //    DummyPicture.show(s,n1);
        //    DummyPicture.write(s,n1,"externKantenGrob3","pdf");
        //    
        List level = n1.getChildrenAtLevel(2);
        level.add(0, level.remove(1));
        //    level = n4.getChildrenAtLevel(1);
        //    level.add(0, level.remove(1));
        //
        n1.getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);

        DummyPicture.write(s, "externKantenGrob2", "pdf");
        s.deleteLeaf(n7);
        s.deleteLeaf(n6);
        s.deleteLeaf(n2);
        DummyPicture.show(s);
        DummyPicture.write(s, "externKantenGrob3", "pdf");
    }

    /**
     * die bilder hier sind mit dummyknoten gemacht
     */
    private static void externeKantenGrob() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());

        //Node n3 = s.newLeaf(n2);
        SugiNode n4 = (SugiNode) s.newLeaf(n1);
        Node n5 = s.newLeaf(n4);
        Node n6 = s.newLeaf(n4);

        //        Node n7 = s.newLeaf(n3);
        s.newEdge(n5, n2);
        s.newEdge(n6, n2);
        //        s.newEdge(n1, n2);
        //        s.newEdge(n2, n3);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = n1.getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        n1.getLocalHierarchy().updatePositions();
        level = s.getMetricRoot().getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        s.deleteLeaf(n2);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.write(s, "externKantenGrob1", "pdf");

        DummyPicture.show(s, n1);
        DummyPicture.write(s, n1, "externKantenGrob3", "pdf");

        level = n1.getChildrenAtLevel(1);
        level.add(1, level.remove(2));
        level = n4.getChildrenAtLevel(1);
        level.add(0, level.remove(1));

        n1.getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "externKantenGrob2", "pdf");
    }

    /**
     *
     */
    private static void graphNotCompact() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(n1);

        Node n3 = s.newLeaf(s.getRoot());

        Node n5 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n5);

        //        Node n6 = s.newLeaf(n5);
        //        Node n7 = s.newLeaf(s.getRoot());
        //        Node n8 = s.newLeaf(n7);
        //        Node n19 = s.newLeaf(n17);
        //        Node n20 = s.newLeaf(s.getRoot());
        //        Node n21 = s.newLeaf(n20);
        Node n9 = s.newLeaf(s.getRoot());
        Node n10 = s.newLeaf(n9);
        Node n11 = s.newLeaf(s.getRoot());
        Node n12 = s.newLeaf(s.getRoot());
        Node n13 = s.newLeaf(s.getRoot());
        Node n14 = s.newLeaf(n12);

        //        Node n15 = s.newLeaf(n12);
        //        Node n16 = s.newLeaf(n12);
        //        Node n33 = s.newLeaf(s.getRoot());
        //        Node n34 = s.newLeaf(n33);
        //        s.newEdge(n4, n6);
        //        s.newEdge(n8, n6);
        s.newEdge(n4, n2);
        s.newEdge(n1, n9);
        s.newEdge(n10, n14);
        //        s.newEdge(n20, n33);
        //        s.newEdge(n34, n32);
        s.newEdge(n5, n11);
        s.newEdge(n5, n13);
        //        s.newEdge(n19, n21);
        //        s.newEdge(n15, n14);
        //        s.newEdge(n16, n14);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level1 = s.getMetricRoot().getChildrenAtLevel(1);
        level1.add(1, level1.remove(2));

        List level2 = s.getMetricRoot().getChildrenAtLevel(2);
        level2.add(2, level2.remove(1));
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s, 0);
        DummyPicture.show(s);
        MetricLayout.layout(s, 1);
        DummyPicture.show(s);
        MetricLayout.layout(s, 2);
        DummyPicture.show(s);
        MetricLayout.layout(s, 3);
        DummyPicture.show(s);
        MetricLayout.layout(s, 4);
        DummyPicture.show(s);
        //        DummyPicture.write(s,"graphNotCompact", "pdf");
    }

    /**
     *
     */
    private static void groupingdreipfade() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n4);
        Node n6 = s.newLeaf(n4);
        Node n7 = s.newLeaf(n3);
        s.newEdge(n5, n7);
        s.newEdge(n6, n7);
        s.newEdge(n1, n2);
        s.newEdge(n2, n3);
        s.newEdge(n4, n7);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = n1.getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        level.add(1, level.remove(3));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(2);
        level.add(0, level.remove(1));
        level.add(1, level.remove(3));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(3);
        level.add(0, level.remove(1));
        level.add(1, level.remove(3));

        n1.getLocalHierarchy().updatePositions();
        ((SugiNode) s.getRoot()).getLocalHierarchy().updatePositions();
        s.deleteLeaf(n2);
        MetricLayout.layout(s);

        List nodes = s.getAllNodes();
        SugiNode n9 = (SugiNode) nodes.get(8);
        SugiNode n16 = (SugiNode) nodes.get(15);

        //        SugiNode n23 = (SugiNode)nodes.get(22);
        s.deactivateAllLHs();
        s.newEdge(n4, n9);
        s.newEdge(n4, n16);
        //        s.newEdge(n4,n23);
        //        s.newEdge(n5,n23);
        //        s.newEdge(n6,n18);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        //        DummyPicture.write(s,"expandnach3", "pdf"); 
        DummyPicture.write(s, "contractzwischenergebnisalternativ", "pdf");
    }

    /**
     *
     */
    private static void groupingzweipfade() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n4);
        Node n6 = s.newLeaf(n4);
        Node n7 = s.newLeaf(n3);
        s.newEdge(n5, n7);
        s.newEdge(n6, n7);
        s.newEdge(n1, n2);
        s.newEdge(n2, n3);
        //        s.newEdge(n4,n7);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = n1.getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        //        level.add(1,level.remove(3));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(2);
        level.add(0, level.remove(1));
        //        level.add(1,level.remove(3));
        level = ((SugiNode) s.getRoot()).getChildrenAtLevel(3);
        level.add(0, level.remove(1));
        //        level.add(1,level.remove(3));
        n1.getLocalHierarchy().updatePositions();
        ((SugiNode) s.getRoot()).getLocalHierarchy().updatePositions();
        s.deleteLeaf(n2);
        MetricLayout.layout(s);

        List nodes = s.getAllNodes();
        SugiNode n9 = (SugiNode) nodes.get(8);
        SugiNode n16 = (SugiNode) nodes.get(15);
        SugiNode n18 = (SugiNode) nodes.get(17);
        s.deactivateAllLHs();
        s.newEdge(n4, n9);
        s.newEdge(n4, n16);
        s.newEdge(n4, n18);
        s.newEdge(n5, n16);
        s.newEdge(n5, n18);
        s.newEdge(n6, n16);
        s.newEdge(n6, n18);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        //        DummyPicture.write(s,"expandvor", "pdf"); 
        //        DummyPicture.write(s,"expandzwischenergebnis", "pdf"); 
        //        DummyPicture.write(s,"expandzwischenergebnisnormalisiert", "pdf"); 
        //        DummyPicture.write(s,"expandzwischenergebnisalternativ", "pdf"); 
        //        DummyPicture.write(s,"expandnach2", "pdf"); 
        //        DummyPicture.write(s,"contractzwischenergebnis", "pdf"); 
    }

    /**
     *
     */
    private static void hauptBeispiel() {
        ViewPanel panel = new ViewPanel();
        Geometry geo = panel.getGeometry();
        View view = geo.getView();
        Node root = view.getRoot();
        Node n2 = view.newLeaf(root);
        Node n1 = view.newLeaf(root);
        Node n3 = view.newLeaf(root);
        Node n4 = view.newLeaf(root);
        Node n5 = view.newLeaf(root);
        Node n6 = view.newLeaf(root);
        Node n1_1 = view.newLeaf(n1);
        Node n1_2 = view.newLeaf(n1);
        Node n1_3 = view.newLeaf(n1);
        Node n2_1 = view.newLeaf(n2);
        Node n2_2 = view.newLeaf(n2);
        Node n3_1 = view.newLeaf(n3);
        Node n3_2 = view.newLeaf(n3);
        Node n3_3 = view.newLeaf(n3);
        Node n4_1 = view.newLeaf(n4);
        Node n5_1 = view.newLeaf(n5);
        Node n5_2 = view.newLeaf(n5);
        Node n6_1 = view.newLeaf(n6);
        Node n6_3 = view.newLeaf(n6);

        view.newEdge(n1, n5);
        view.newEdge(n1_1, n1_2);
        view.newEdge(n1_1, n1_3);
        view.newEdge(n1_3, n3_3);
        view.newEdge(n2, n3_2);
        view.newEdge(n2_1, n2_2);
        view.newEdge(n2, n4);
        view.newEdge(n3_1, n3_3);
        view.newEdge(n3_2, n3_3);
        view.newEdge(n4_1, n6_1);
        view.newEdge(n5_1, n5_2);
        view.newEdge(n6_1, n6_3);
        view.newEdge(n3_2, n6_1);
        view.newEdge(n3_3, n6_1);
        view.newEdge(n3_3, n5);
        DummyPicture.showIds = false;

        //        ((SugiyamaDrawingStyle) geo.getDrawingStyle()).setDrawingStyle(SugiyamaDrawingStyle.FINAL_STYLE);
        //        geo.redraw();
        //        DummyPicture.show(geo);
        SugiyamaDrawingStyle sugi = new SugiyamaDrawingStyle(geo);

        //        geo.setDrawingStyle(new DefaultDrawingStyle(geo));
        //        view.contract(n3);
        //        sugi.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);        
        //        geo.setDrawingStyle(sugi);
        //        
        //        DummyPicture.show(sugi.s);
        ////        DummyPicture.write(sugi.s,"sugi_contracted","pdf");
        //        view.expand(n3);
        //        DummyPicture.show(sugi.s);
        ////        DummyPicture.write(sugi.s,"sugi_expanded_update","pdf");        
        //        geo.redraw();
        //        DummyPicture.show(sugi.s);
        //        DummyPicture.write(sugi.s,"sugi_expanded_staticlayout","pdf");        
        geo.setDrawingStyle(new DefaultDrawingStyle(geo));
        view.contract(n3);
        sugi.setDrawingStyle(SugiyamaDrawingStyle.FINAL_STYLE);
        geo.setDrawingStyle(sugi);
        DummyPicture.showLevels = true;
        DummyPicture.show(geo);
        //        DummyPicture.show(sugi.s, false);
        //        DummyPicture.show(sugi.s, true);
        //        DummyPicture.write(geo,"geo_contracted","pdf");
        DummyPicture.write(geo, "hauptgeocontractedwithLevel", "pdf");
        //        view.expand(n3);
        //        DummyPicture.show(geo);
        ////        DummyPicture.write(geo,"hauptgeoexpandedupdate","pdf");
        //
        //        geo.redraw();
        //        DummyPicture.show(geo);
        //        DummyPicture.write(geo,"hauptgeoexpandedstaticlayout","pdf");
    }

    /**
     *
     */
    private static void hilfsknotenfuerhorizontal() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(n1);
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(n2);
        Node n7 = s.newLeaf(n3);
        Node n8 = s.newLeaf(n4);
        Node n9 = s.newLeaf(n3);
        Node n10 = s.newLeaf(n1);
        Node n11 = s.newLeaf(n4);
        Node n12 = s.newLeaf(n2);
        s.newEdge(n10, n3);
        s.newEdge(n6, n9);
        s.newEdge(n7, n8);
        s.newEdge(n2, n5);
        s.newEdge(n4, n5);
        s.newEdge(n11, n12);
        //    s.newEdge(n, n);
        DummyPicture.showIds = false;
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, n1, "hilfsknotenfuerhorizontal1", "pdf");

        Node n13 = s.newDummyLeaf(n1, 1, 0, DummyNode.NORMAL);
        Node n14 = s.newDummyLeaf(n1, 1, -1, DummyNode.NORMAL);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        s.newEdge(n13, n2);
        s.newEdge(n13, n3);
        s.newEdge(n14, n3);
        s.newEdge(n14, n4);

        DummyPicture.show(s);
        DummyPicture.show(s, n1);
        DummyPicture.write(s, n1, "hilfsknotenfuerhorizontal2", "pdf");
    }

    /**
     *
     */
    private static void horizontalDirekt() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);

        //             Node n4 = s.newLeaf(n1);
        Node n2_1 = s.newLeaf(n2);
        Node n2_2 = s.newLeaf(n2);
        Node n2_3 = s.newLeaf(n2);
        s.newEdge(n2_1, n2_2);
        s.newEdge(n2_2, n2_3);
        s.newEdge(n3, n2_2);

        Node n9 = s.newLeaf(s.getRoot());
        Node n99 = s.newLeaf(s.getRoot());
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        //             MetricLayout.layout(s);
        //             DummyPicture.show(s);
        //DummyPicture.write(s, "ubogen1","pdf");
        List level = s.getMetricRoot().getChildrenAtLevel(1);
        level.remove(n9);
        level.remove(n99);
        level.add(1, n9);
        level.add(2, n99);
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);
        //DummyPicture.write(s, "horizontalDirekt","pdf");
    }

    /**
     *
     */
    private static void horizontalEdgeAcceptable() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        SugiNode n1 = (SugiNode) s.newLeaf(n0);
        Node n2 = s.newLeaf(n0);
        SugiNode n3 = (SugiNode) s.newLeaf(n1);

        Node n4 = s.newLeaf(n0);

        //        Node n2_1 = s.newLeaf(n2);
        Node n2_2 = s.newLeaf(n2);

        //        Node n2_3 = s.newLeaf(n2);
        //        s.newEdge(n2_1, n2_2);
        //        Edge e1 = s.newEdge(n2_2, n2_3);
        s.newEdge(n3, n2_2);
        s.newEdge(n2_2, n4);

        //        Node n9 = s.newLeaf(n0);
        //        Node n99 = s.newLeaf(s.getRoot());
        Hierarchization.hierarchize(s);
        //n3.setClev(n1.getClev().getSubLevel(2));
        Normalization.normalize(s);
        VertexOrdering.order(s);

        //             MetricLayout.layout(s);
        //             DummyPicture.show(s);
        //DummyPicture.write(s, "ubogen1","pdf");
        List level = ((SugiNode) n0).getChildrenAtLevel(1);
        level.add(1, level.remove(2));
        //        level.remove(n99);
        //        level.add(1, n9);
        //        level.add(2, n99);
        ((SugiNode) n0).getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        //        s.deleteEdge(e1);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "horizontalEdgeAcceptable", "pdf");
    }

    /**
     *
     */
    private static void horizontalEdgeInacceptable() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        SugiNode n1 = (SugiNode) s.newLeaf(n0);
        Node n2 = s.newLeaf(n0);
        SugiNode n3 = (SugiNode) s.newLeaf(n1);

        Node n4 = s.newLeaf(n0);
        Node n2_1 = s.newLeaf(n2);
        Node n2_2 = s.newLeaf(n2);
        Node n2_3 = s.newLeaf(n2);
        s.newEdge(n2_1, n2_2);
        s.newEdge(n2_2, n2_3);
        s.newEdge(n3, n2_3);
        s.newEdge(n2_1, n4);

        //        Node n9 = s.newLeaf(n0);
        //        Node n99 = s.newLeaf(s.getRoot());
        Hierarchization.hierarchize(s);
        n3.setClev(n1.getClev().getSubLevel(2));
        Normalization.normalize(s);
        VertexOrdering.order(s);

        //             MetricLayout.layout(s);
        //             DummyPicture.show(s);
        //DummyPicture.write(s, "ubogen1","pdf");
        List level = ((SugiNode) n0).getChildrenAtLevel(1);
        level.add(1, level.remove(2));
        //        level.remove(n99);
        //        level.add(1, n9);
        //        level.add(2, n99);
        ((SugiNode) n0).getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        s.deleteLeaf(n2_2);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        //        DummyPicture.write(s, "horizontalEdgeinacceptable","pdf");
    }

    /**
     *
     */
    private static void horizontalEdgesimple() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        Node n1 = s.newLeaf(n0);
        Node n2 = s.newLeaf(n0);
        Node n3 = s.newLeaf(n1);

        Node n2_2 = s.newLeaf(n2);
        s.newEdge(n3, n2_2);

        Node n9 = s.newLeaf(n0);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = ((SugiNode) n0).getChildrenAtLevel(1);
        level.remove(n9);
        level.add(1, n9);
        ((SugiNode) n0).getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "horizontalEdgesimple", "pdf");
    }

    /**
     *
     */
    private static void horizontalInDirekt() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);

        //             Node n4 = s.newLeaf(n1);
        Node n2_1 = s.newLeaf(n2);
        Node n2_2 = s.newLeaf(n2);
        Node n2_3 = s.newLeaf(n2);
        s.newEdge(n2_1, n2_2);
        s.newEdge(n2_2, n2_3);
        s.newEdge(n3, n2_3);

        Node n9 = s.newLeaf(s.getRoot());
        Node n99 = s.newLeaf(s.getRoot());
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        //             MetricLayout.layout(s);
        //             DummyPicture.show(s);
        //DummyPicture.write(s, "ubogen1","pdf");
        List level = s.getMetricRoot().getChildrenAtLevel(1);
        level.remove(n9);
        level.remove(n99);
        level.add(1, n9);
        level.add(3, n99);
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);
        // DummyPicture.write(s, "horizontalIndirekt","pdf");
    }

    /**
     *
     */
    private static void independentsubgraphs() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(s.getRoot());
        Node n5 = s.newLeaf(s.getRoot());
        Node n6 = s.newLeaf(s.getRoot());
        Node n1_2 = s.newLeaf(n1);
        Node n2_2 = s.newLeaf(n2);
        Node n3_1 = s.newLeaf(n3);
        Node n4_1 = s.newLeaf(n4);
        Node n5_1 = s.newLeaf(n5);
        Node n5_2 = s.newLeaf(n5);
        Node n6_1 = s.newLeaf(n6);
        Node n6_2 = s.newLeaf(n6);

        s.newEdge(n1_2, n2_2);
        s.newEdge(n2_2, n5_2);
        s.newEdge(n3_1, n4_1);
        s.newEdge(n5_1, n6_1);
        s.newEdge(n5_2, n6_2);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = ((SugiNode) s.getRoot()).getChildrenAtLevel(1);
        level.add(2, level.remove(4));
        level.add(3, level.remove(5));
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.write(s, "independentsubgraphsGraph", "pdf");

        SugiNode d1 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        SugiNode d2 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        SugiNode d3 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        SugiNode d4 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        s.newEdge(d1, n1);
        s.newEdge(d1, n2);
        s.newEdge(d2, n3);
        s.newEdge(d2, n4);
        s.newEdge(d3, n2);
        s.newEdge(d3, n5);
        s.newEdge(d4, n5);
        s.newEdge(d4, n6);
        MetricLayout.layout(s);
        DummyPicture.show(s, (SugiNode) s.getRoot());
        DummyPicture.write(
            s, (SugiNode) s.getRoot(), "independentsubgraphsHier", "pdf");
    }

    /**
     *
     */
    private static void innersegmentcrossing() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(s.getRoot());
        Node n4_2 = s.newLeaf(s.getRoot());
        Node n6 = s.newLeaf(s.getRoot());
        Node n4_3 = s.newLeaf(s.getRoot());

        //  Node n8 = s.newLeaf(n5);
        //  Node n9 = s.newLeaf(n5);
        s.newEdge(n1, n2);
        s.newEdge(n2, n3);
        s.newEdge(n3, n4);
        s.newEdge(n3, n4_2);
        s.newEdge(n3, n4_3);
        s.newEdge(n1, n4_2);
        //  s.newEdge(n1,n4);
        s.newEdge(n6, n4);
        //  s.newEdge(n6,n4_2);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s, 1);

        List level2 = s.getMetricRoot().getChildrenAtLevel(2);
        List level3 = s.getMetricRoot().getChildrenAtLevel(3);
        Collections.swap(level3, 1, 2);

        List level4 = s.getMetricRoot().getChildrenAtLevel(4);
        Collections.swap(level4, 1, 2);
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        ((SugiNode) level3.get(1)).setAbsoluteX(
            ((SugiNode) level2.get(1)).getAbsoluteX());
        ((SugiNode) level3.get(2)).setAbsoluteX(
            ((SugiNode) level2.get(2)).getAbsoluteX());
        ((SugiNode) level4.get(1)).setAbsoluteX(
            ((SugiNode) level2.get(1)).getAbsoluteX());
        ((SugiNode) level4.get(2)).setAbsoluteX(
            ((SugiNode) level2.get(2)).getAbsoluteX());
        DummyPicture.show(s);
        //  DummyPicture.write(s,"innersegmentcrossing", "pdf");
    }

    /**
     *
     */
    private static void intermediateResult() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n1);

        //        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(n1);
        Node n7 = s.newLeaf(n1);
        Node n8 = s.newLeaf(s.getRoot());
        Node n9 = s.newLeaf(s.getRoot());

        //        Node n10 = s.newLeaf(s.getRoot());
        Node n11 = s.newLeaf(s.getRoot());
        Node n12 = s.newLeaf(s.getRoot());
        s.newEdge(n1, n2);
        s.newEdge(n2, n3);
        s.newEdge(n2, n8);
        s.newEdge(n1, n3);

        s.newEdge(n4, n6);
        s.newEdge(n4, n7);

        s.newEdge(n4, n9);
        //        s.newEdge(n5, n10);
        s.newEdge(n6, n11);
        s.newEdge(n7, n12);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = s.getMetricRoot().getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        //        level.add(level.remove(0));
        //        level.add(3,level.remove(4));
        level = s.getMetricRoot().getChildrenAtLevel(2);
        level.add(2, level.remove(0));
        //        level.add(level.remove(3));
        //        level.add(level.remove(2));
        //        level.add(level.remove(1));
        //        level.add(4,level.remove(5));
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        //        n1.getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        s.deleteLeaf(n2);
        s.deleteLeaf(n8);
        s.newEdge(
            (Node) s.getAllNodes().get(12), (Node) s.getAllNodes().get(9));
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "intermediateResult2", "pdf");
        s.deleteLeaf(n3);
        DummyPicture.write(s, "intermediateResult", "pdf");
    }

    /**
     *
     */
    private static void inzidenteKanten() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(n1);
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n1);
        Node n6 = s.newLeaf(n1);
        SugiNode n7 = (SugiNode) s.newLeaf(s.getRoot());
        Node n8 = s.newLeaf(n7);

        //        Node n = s.newLeaf(n);
        s.newEdge(n2, n5);
        s.newEdge(n3, n6);
        s.newEdge(n4, n5);
        s.newEdge(n4, n8);
        //            s.newEdge(n, n);
        DummyPicture.showIds = false;
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "inzidenteKanten1", "pdf");
        s.deleteLeaf(n8);
        s.deleteLeaf(n7);
        MetricLayout.layout(s);

        s.deleteLeaf(n4);

        DummyPicture.show(s);
        DummyPicture.write(s, "inzidenteKanten2", "pdf");
    }

    /**
     *
     */
    private static void localHierarchy() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        //        Node n0 = s.newLeaf(s.getRoot());
        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n2);
        Node n6 = s.newLeaf(n2);
        Node n7 = s.newLeaf(n3);
        Node n8 = s.newLeaf(n1);
        Node n9 = s.newLeaf(n2);
        Node n10 = s.newLeaf(n2);
        Node n11 = s.newLeaf(n2);
        Node n12 = s.newLeaf(n3);
        Node n13 = s.newLeaf(n8);
        Node n14 = s.newLeaf(n9);
        Node n15 = s.newLeaf(n9);
        Node n16 = s.newLeaf(n10);
        Node n17 = s.newLeaf(n11);
        Node n18 = s.newLeaf(n12);
        Node n19 = s.newLeaf(n2);
        s.newEdge(n4, n8);
        s.newEdge(n4, n9);
        s.newEdge(n5, n9);
        s.newEdge(n6, n10);
        s.newEdge(n7, n12);
        s.newEdge(n8, n19);
        s.newEdge(n9, n19);
        s.newEdge(n10, n19);
        s.newEdge(n14, n13);
        s.newEdge(n14, n15);
        s.newEdge(n16, n15);
        s.newEdge(n16, n17);
        s.newEdge(n18, n17);
        //        s.newEdge(n, n);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.showIds = false;
        DummyPicture.show(s);
        DummyPicture.show(s, (SugiNode) n2);
        DummyPicture.write(s, "localHierarchyGraph", "pdf");
        DummyPicture.write(s, (SugiNode) n2, "localHierarchyHierarchy", "pdf");
    }

    /**
     *
     */
    private static void localersetzung() {
        DummyPicture.showIds = false;

        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(n1);
        SugiNode n3 = (SugiNode) s.newLeaf(n1);
        SugiNode n4 = (SugiNode) s.newLeaf(n1);
        SugiNode n5 = (SugiNode) s.newLeaf(n2);
        s.newEdge(n5, n4);
        s.newEdge(n2, n3);

        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s, n1);
        DummyPicture.write(s, n1, "localersetzungVor", "pdf");
        s.newEdge(n2, n4);

        Node d1 = (Node) n1.getChildrenAtLevel(1).get(1);
        s.deleteLeaf((Node) s.getChildren(d1).get(0));
        s.deleteLeaf(d1);
        DummyPicture.show(s, n1);
        DummyPicture.write(s, n1, "localersetzungNach", "pdf");
    }

    /**
     *
     */
    private static void nonproperedge() {
        DummyPicture.showIds = false;

        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n4);
        Node n6 = s.newLeaf(n3);
        s.newEdge(n5, n6);
        s.newEdge(n1, n2);
        s.newEdge(n2, n3);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);

        SugiNode n7 = (SugiNode) ((SugiNode) n1).getChildrenAtLevel(1).get(1);
        SugiNode n9 =
            (SugiNode) ((SugiNode) s.getRoot()).getChildrenAtLevel(1).get(1);
        SugiNode n11 =
            (SugiNode) ((SugiNode) s.getRoot()).getChildrenAtLevel(3).get(1);
        s.deactivateAllLHs();
        s.newEdge(n7, n6);
        s.newEdge(n9, n11);
        s.newEdge(n5, n6);
        s.deleteLeaf(n2);
        DummyPicture.show(s);
        //        DummyPicture.write(s,"nonproperedge", "pdf");
    }

    /**
     *
     */
    private static void nonproperedgeexternalpath() {
        DummyPicture.showIds = false;

        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n3 = (SugiNode) s.newLeaf(n1);
        SugiNode n4 = (SugiNode) s.newLeaf(n2);

        //        s.newLeaf(s.getRoot());
        //        s.newLeaf(s.getRoot());
        s.newEdge(n3, n4);

        //        Hierarchization.hierarchize(s);
        CompoundLevel rootClev = CompoundLevel.getClevForRoot();
        s.getMetricRoot().setClev(rootClev);
        n1.setClev(rootClev.getSubLevel(1));
        n2.setClev(rootClev.getSubLevel(1));
        n3.setClev(n1.getClev().getSubLevel(1));
        n4.setClev(n2.getClev().getSubLevel(4));

        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        //        DummyPicture.write(s, "nonproperedgeexternalpath", "pdf");
    }

    /**
     *
     */
    private static void nonproperedgeexternalpathcomplicated() {
        DummyPicture.showIds = false;

        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        //        SugiNode n0 = (SugiNode) s.newLeaf(s.getRoot());
        //        SugiNode n1 = (SugiNode) s.newLeaf(n0);
        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());

        //        SugiNode n2 = (SugiNode) s.newLeaf(n0);
        SugiNode n2 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n3 = (SugiNode) s.newLeaf(n1);
        SugiNode n4 = (SugiNode) s.newLeaf(n2);
        SugiNode n5 = (SugiNode) s.newLeaf(n3);
        SugiNode n6 = (SugiNode) s.newLeaf(n4);
        SugiNode n7 = (SugiNode) s.newLeaf(n5);
        SugiNode n8 = (SugiNode) s.newLeaf(n6);

        //        s.newLeaf(s.getRoot());
        //        s.newLeaf(s.getRoot());
        s.newEdge(n7, n8);

        //        Hierarchization.hierarchize(s);
        CompoundLevel rootClev = CompoundLevel.getClevForRoot();
        s.getMetricRoot().setClev(rootClev);

        //        n0.setClev(rootClev.getSubLevel(1));
        //        rootClev = n0.getClev();
        n1.setClev(rootClev.getSubLevel(1));
        n2.setClev(rootClev.getSubLevel(1));
        n3.setClev(n1.getClev().getSubLevel(1));
        n4.setClev(n2.getClev().getSubLevel(1));
        n5.setClev(n3.getClev().getSubLevel(1));
        n6.setClev(n4.getClev().getSubLevel(1));
        n7.setClev(n5.getClev().getSubLevel(1));
        n8.setClev(n6.getClev().getSubLevel(4));

        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);

        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.show(s);
        DummyPicture.write(s, "nonproperedgeexternalpathcomplicated", "pdf");
    }

    /**
     *
     */
    private static void nonproperedgelocalpath() {
        DummyPicture.showIds = false;

        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(s.getRoot());

        //        s.newLeaf(s.getRoot());
        //        s.newLeaf(s.getRoot());
        s.newEdge(n1, n2);

        //        Hierarchization.hierarchize(s);
        CompoundLevel rootClev = CompoundLevel.getClevForRoot();
        s.getMetricRoot().setClev(rootClev);
        n1.setClev(rootClev.getSubLevel(1));
        n2.setClev(rootClev.getSubLevel(4));
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.showIds = false;
        DummyPicture.showLevelNumbers = false;
        DummyPicture.write(s, "nonproperedgelocalpath", "pdf");
    }

    /**
     *
     */
    private static void polylinie() {
        //damit das bild funktioniert, muss SugiNode.baryFlag= true sein.
        ViewPanel panel = new ViewPanel();
        Geometry geo = panel.getGeometry();
        View s = geo.getView();
        ((SugiyamaDrawingStyle) geo.getDrawingStyle()).setDrawingStyle(
            SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n1 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(s.getRoot());
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n4);
        Node n7 = s.newLeaf(n3);
        s.newEdge(n5, n7);
        s.newEdge(n1, n3);
        DummyPicture.showIds = false;
        DummyPicture.showLevels = false;

        geo.redraw();
        DummyPicture.show(geo);
        //        DummyPicture.write(geo,"singleexpandededgepath", "pdf"); 
        //        DummyPicture.write(geo,"polyliniestartframe", "pdf"); 
        geo.setDrawingStyle(new DefaultDrawingStyle(geo), false);
        s.contract(n4);
        geo.setDrawingStyle(new SugiyamaDrawingStyle(geo));
        ((SugiyamaDrawingStyle) geo.getDrawingStyle()).setDrawingStyle(
            SugiyamaDrawingStyle.DEBUG_STYLE);
        geo.redraw();
        DummyPicture.show(geo);
        //        DummyPicture.write(geo,"polyliniezielframe", "pdf"); 
    }

    /**
     *
     */
    private static void problemcontract() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        Node v = s.newLeaf(n0);
        Node n3 = s.newLeaf(n0);
        Node u = s.newLeaf(n3);
        Edge e1 = s.newEdge(v, n3);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        s.deactivateAllLHs();
        s.deleteEdge(e1);
        s.newEdge(v, u);
        DummyPicture.showLevelNumbers = false;
        DummyPicture.showIds = false;
        DummyPicture.show(s);

        DummyPicture.write(s, "problemcontract1", "pdf");
    }

    /**
     *
     */
    private static void problemctract2() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        Node n0 = s.newLeaf(s.getRoot());
        Node v = s.newLeaf(n0);
        Node n2 = s.newLeaf(n0);
        Node u = s.newLeaf(n2);
        Node n4 = s.newLeaf(v);
        Node n5 = s.newLeaf(v);
        Edge e1 = s.newEdge(v, n2);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        s.deactivateAllLHs();
        s.deleteEdge(e1);
        s.newEdge(n4, u);
        s.newEdge(n5, u);
        DummyPicture.show(s);
        DummyPicture.write(s, "problemcontract2", "pdf");
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "problemcontract3", "pdf");

        //        ViewPanel panel = new ViewPanel();
        //        Geometry geo = panel.getGeometry();
        //        View v = geo.getView();
        //         n1 = v.newLeaf(v.getRoot());
        //         n2 = v.newLeaf(v.getRoot());
        //         n3 = v.newLeaf(n1);
        //         e1 = v.newEdge(n3, n2);
        //         ((SugiyamaDrawingStyle)geo.getDrawingStyle()).setDrawingStyle(SugiyamaDrawingStyle.FINAL_STYLE);
        //         geo.redraw();
        //         DummyPicture.show(geo);
    }

    /**
     *
     */
    private static void proxynodeproblem2() {
        //        DummyPicture.showIds = false;
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(n1);
        SugiNode n3 = (SugiNode) s.newLeaf(n1);
        SugiNode n4 = (SugiNode) s.newLeaf(n2);
        SugiNode n5 = (SugiNode) s.newLeaf(n3);
        SugiNode n6 = (SugiNode) s.newLeaf(n4);
        SugiNode n7 = (SugiNode) s.newLeaf(n5);
        SugiNode n8 = (SugiNode) s.newLeaf(n4);
        SugiNode n9 = (SugiNode) s.newLeaf(n5);
        s.newEdge(n2, n3);

        Hierarchization.hierarchize(s);
        //        CompoundLevel rootClev = CompoundLevel.getClevForRoot();
        //        s.getMetricRoot().setClev(rootClev);
        //        n1.setClev(rootClev.getSubLevel(1));
        //        n2.setClev(rootClev.getSubLevel(4));
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        s.newEdge(n6, n7);
        s.newEdge(n9, n8);
        DummyPicture.show(s);
        //        DummyPicture.write(s, "proxynodeproblem2", "pdf");
    }

    /**
     *
     */
    private static void proxynodeproblem3() {
        //      DummyPicture.showIds = false;
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        SugiNode n1 = (SugiNode) s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(n1);
        SugiNode n3 = (SugiNode) s.newLeaf(n1);
        SugiNode n4 = (SugiNode) s.newLeaf(n2);
        SugiNode n5 = (SugiNode) s.newLeaf(n3);
        SugiNode n6 = (SugiNode) s.newLeaf(n4);
        SugiNode n7 = (SugiNode) s.newLeaf(n5);
        SugiNode n8 = (SugiNode) s.newLeaf(n4);
        SugiNode n9 = (SugiNode) s.newLeaf(n5);

        //      s.newEdge(n2,n3);
        s.newEdge(n6, n7);
        s.newEdge(n9, n8);

        Hierarchization.hierarchize(s);
        //      CompoundLevel rootClev = CompoundLevel.getClevForRoot();
        //      s.getMetricRoot().setClev(rootClev);
        //      n1.setClev(rootClev.getSubLevel(1));
        //      n2.setClev(rootClev.getSubLevel(4));
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        //      DummyPicture.write(s, "proxynodeproblem3", "pdf");
    }

    /**
     *
     */
    private static void smallViewExpand() {
        ViewPanel panel = new ViewPanel();
        Geometry geo = panel.getGeometry();
        View view = geo.getView();
        Node root = view.getRoot();
        Node n1 = root;
        Node n2 = view.newLeaf(n1);
        Node n3 = view.newLeaf(n1);
        Node n4 = view.newLeaf(n1);
        Node n5 = view.newLeaf(n2);
        Node n6 = view.newLeaf(n2);

        view.newEdge(n5, n3);
        view.newEdge(n6, n3);
        view.newEdge(n6, n5);
        view.newEdge(n2, n3);
        view.newEdge(n2, n4);
        DummyPicture.showIds = false;
        ((SugiyamaDrawingStyle) geo.getDrawingStyle()).setDrawingStyle(
            SugiyamaDrawingStyle.FINAL_STYLE);
        geo.redraw();
        DummyPicture.show(geo);
        //        DummyPicture.write(geo,"smallCompoundgraph","pdf");
        geo.setDrawingStyle(new DefaultDrawingStyle(geo));
        view.contract(n2);
        geo.setDrawingStyle(
            new SugiyamaDrawingStyle(geo, SugiyamaDrawingStyle.FINAL_STYLE));
        DummyPicture.show(geo);
        //        DummyPicture.write(geo,"smallView","pdf");
        //        geo.setDrawingStyle(new DefaultDrawingStyle(geo));
        //        view.contract(n6);
        //        geo.setDrawingStyle(new SugiyamaDrawingStyle(geo,
        //                SugiyamaDrawingStyle.FINAL_STYLE));
        //        DummyPicture.show(geo);
        //        view.expand(n2);
        //        view.expand(n7);
        //        view.expand(n12);
        //        DummyPicture.show(geo);
        //        DummyPicture.write(geo,"compound_generalSugiViewExpanded","pdf");
    }

    /**
     *
     */
    private static void ubogen() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        //Node n0 = s.newLeaf(s.getRoot());
        Node n1 = s.newLeaf(s.getRoot());
        Node n2 = s.newLeaf(s.getRoot());
        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n1);
        Node n5 = s.newLeaf(n2);
        s.newEdge(n3, n4);
        s.newEdge(n3, n5);
        s.newEdge(n4, n5);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);
        MetricLayout.layout(s);
        DummyPicture.show(s);

        DummyPicture.showIds = false;

        DummyPicture.write(s, "ubogenwishedGraph", "pdf");

        Node n6 = (Node) ((SugiNode) s.getRoot()).getChildrenAtLevel(1).get(1);
        SugiNode d1 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        SugiNode d2 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        SugiNode d3 =
            s.newDummyLeaf((SugiNode) s.getRoot(), 0, DummyNode.UNKNOWN);
        s.newEdge(d1, n1);
        s.newEdge(d1, n6);
        s.newEdge(d2, n1);
        s.newEdge(d2, n2);
        s.newEdge(d3, n6);
        s.newEdge(d3, n2);
        MetricLayout.layout(s);
        DummyPicture.show(s, (SugiNode) s.getRoot());
        DummyPicture.write(
            s, (SugiNode) s.getRoot(), "ubogenwishedHier", "pdf");

        List level = s.getMetricRoot().getChildrenAtLevel(1);
        level.add(1, level.remove(2));
        level = s.getMetricRoot().getChildrenAtLevel(0);
        level.add(0, level.remove(1));
        s.getMetricRoot().getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s, (SugiNode) s.getRoot());
        DummyPicture.write(
            s, (SugiNode) s.getRoot(), "ubogenunwishedHier", "pdf");

        s.deleteLeaf(d3);
        s.deleteLeaf(d2);
        s.deleteLeaf(d1);
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.write(s, "ubogenunwishedGraph", "pdf");
    }

    /**
     *
     */
    private static void ukantewegenLR() {
        SugiCompoundGraph s = new SugiCompoundGraph();
        s.setDrawingStyle(SugiyamaDrawingStyle.DEBUG_STYLE);

        //        Node n0 = s.newLeaf(s.getRoot());
        Node n1 = s.newLeaf(s.getRoot());
        SugiNode n2 = (SugiNode) s.newLeaf(s.getRoot());

        Node n3 = s.newLeaf(n1);
        Node n4 = s.newLeaf(n3);
        Node n5 = s.newLeaf(n3);
        Node n6 = s.newLeaf(n2);
        Node n7 = s.newLeaf(n2);
        Node n8 = s.newLeaf(n2);
        Node n9 = s.newLeaf(n7);
        Node n10 = s.newLeaf(n6);
        Node n11 = s.newLeaf(n6);
        Node n12 = s.newLeaf(n7);
        Node n13 = s.newLeaf(n8);

        Node n14 = s.newLeaf(n1);
        Node n15 = s.newLeaf(n6);

        s.newEdge(n11, n14);
        s.newEdge(n3, n14);
        s.newEdge(n4, n11);
        s.newEdge(n10, n5);
        s.newEdge(n10, n11);
        //        s.newEdge(n12, n11);
        s.newEdge(n12, n13);
        s.newEdge(n12, n9);
        s.newEdge(n9, n15);
        //        s.newEdge(n, n);
        //        s.newEdge(n, n);
        Hierarchization.hierarchize(s);
        Normalization.normalize(s);
        VertexOrdering.order(s);

        List level = n2.getChildrenAtLevel(1);
        level.add(0, level.remove(1));
        n2.getLocalHierarchy().updatePositions();
        MetricLayout.layout(s);
        DummyPicture.show(s);
        DummyPicture.show(s, n2);
        DummyPicture.showIds = false;
        DummyPicture.write(s, "ukantewegenLR1", "pdf");
        DummyPicture.write(s, n2, "ukantewegenLR2", "pdf");
    }
}
