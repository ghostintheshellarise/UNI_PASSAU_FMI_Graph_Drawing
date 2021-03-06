/*==============================================================================
*
*   Profiler.java
*
*   @author Michael Proepster
*
*==============================================================================
* $Id: Profiler.java 907 2005-10-19 12:03:31Z raitner $
*/

package org.visnacom.sugiyama.eval;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * centralized collector for all measuring values of a test run. run times can
 * be mearsured by using the start and stop method. other values can be added
 * by using addValue. all results are written to a TestResult object and can
 * be written into a database.
 */
public class Profiler {
    //~ Static fields/initializers =============================================

    /* indicates whether profiling is turned on; call reset() to do so */
    private static boolean active = false;
    private static boolean database_on = false;
    public static TestResult currentTest;

    /*only provisoric*/
    private static final List savedResults = new LinkedList();

    /** the database connection */
    public static Connection conn = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement insertExpandStatement;

    /*constants for the columns in the database,
     * the actual string values have no meaning, they should only be distinct */
    public static final String NUM_NODES = "numNodesDef";
    public static final String DENSITY_DEF = "density";
    public static final String NUM_EDGES_ACT = "numEdgesAct";
    public static final String MEAN_COMPLEXITY_DEF = "meanComplexity";
    public static final String MEAN_COMPLEXITY_ACT = "meanComplexityActual";
    public static final String SEED = "seed";
    public static final String MEAN_DEGREE_DEF = "meanDegreeDef";
    public static final String MEAN_DEGREE_ACT = "meanDegreeActual";
    public static final String TREE_HEIGHT = "treeHeight";
    public static final String NUM_DUMMY_NODES = "numDummyNodes";
    public static final String NUM_EDGE_SEGMENTS = "numEdgeSegments";
    public static final String STATIC_CROSSINGS = "crossings";
    public static final String AREA_WIDTH = "areaWidth";
    public static final String AREA_HEIGHT = "areaHeight";
    public static final String TIME_STATIC_LAYOUT = "staticlayout";
    public static final String TIME_HIERARCHIZATION = "hierarchize";
    public static final String TIME_NORMALIZATION = "normalize";
    public static final String TIME_VERTEX_ORDERING = "vertexordering";
    public static final String TIME_METRICAL_LAYOUT = "metriclayout";
    public static final String TIME_GRAPH_CREATION = "graphcreation";
    public static final String ALLOWED_EDGES = "allowedEdges";
    public static final String REL_COMPLEXITY_DEF = "relComplexityDef";
    public static final String MAX_COMPLEXITY = "maxComplexity";
    public static final String EXPANDED_AREA_WIDTH = "expandedAreaW";
    public static final String EXPANDED_AREA_HEIGHT = "expandedAreaH";
    public static final String STATIC_CUTS = "cuts";
    public static final String EXPAND_CROSSINGS = "expCrossings";
    public static final String EXPAND_CUTS = "expCuts";
    public static final String TIME_EXPAND = "timeExapnd";
    public static final String NUM_EXPANDS = "numExpands";

    /*this contains the constants in the right order for the database table */
    private static String[] tableColumns =
        {
            
            /*
             * this is exactly the sql statement to create the table:
             *
                 CREATE TABLE  (
             */
            "time_stamp", // timestamp,
            NUM_NODES, // int, 
            DENSITY_DEF, // real, 
            MEAN_DEGREE_DEF, // real, 
            REL_COMPLEXITY_DEF, // real,
            NUM_EDGES_ACT, // int, 
            ALLOWED_EDGES, // int,
            MEAN_DEGREE_ACT, // real, 
            TREE_HEIGHT, // int, 
            MEAN_COMPLEXITY_ACT, // real,
            MAX_COMPLEXITY, // int,
            NUM_DUMMY_NODES, // int, 
            NUM_EDGE_SEGMENTS, // int, 
            AREA_WIDTH, // int, 
            AREA_HEIGHT, // int, 
            TIME_STATIC_LAYOUT, // int, 
            TIME_HIERARCHIZATION, // int, 
            TIME_NORMALIZATION, // int, 
            TIME_VERTEX_ORDERING, // int, 
            TIME_METRICAL_LAYOUT, // int, 
            TIME_GRAPH_CREATION, // int, 
            MEAN_COMPLEXITY_DEF, // real, 
            SEED, // int
            EXPANDED_AREA_WIDTH, // int,
            EXPANDED_AREA_HEIGHT, // int,
            STATIC_CROSSINGS, // int,
            STATIC_CUTS, // int,
            EXPAND_CROSSINGS, // int,
            EXPAND_CUTS, // int,
            TIME_EXPAND, // int
            NUM_EXPANDS // int
        //run_id bigserial );
        };

    //this values are not directly stored in some column, but in a extra table
    public static final String TIME_SINGLE_EXPAND = "singleExpand";
    public static final String TIME_SINGLE_STATIC = "singleSTATIC";

    //~ Methods ================================================================

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static boolean isActive() {
        return active;
    }

    /**
     *
     */
    public static void printResults() {
        //        System.out.println(savedResults);
        System.out.println(currentTest);
    }

    /**
     * resets the TestResult object. should be called at the beginning of a
     * test run. it activates the profiling, but not the database.
     */
    public static void reset() {
        currentTest = new TestResult();
        //                Evaluation.currentTest = currentTest;
        active = true;
    }

    /**
     * resets the given parameter
     *
     * @param identifier DOCUMENT ME!
     */
    public static void reset(String identifier) {
        if(!active) {
            return;
        }

        Object content = currentTest.values.remove(identifier);
        assert content != null;
    }

    /**
     * saves the current TestResult object. should be called at the end of a
     * test run. if the database is turned on, stores it there. otherwise just
     * add it to a list.
     */
    public static void saveTestResult() {
        if(!active) {
            return;
        }

        if(database_on) {
            try {
                ensureConnection();
                ensureInsertStats();
                if(insertStatement != null) {
                    insertStatement.clearParameters();
                    for(int i = 1; i < tableColumns.length; i++) {
                        fillParameter(insertStatement, tableColumns[i], i);
                    }

                    insertStatement.execute();
                }

                if(insertExpandStatement != null) {
                    insertExpandStatement.clearParameters();
                    insertExpandStatement.setObject(1,
                        (currentTest.values.get(SEED)));
                    for(Iterator it = currentTest.timeValues.iterator();
                        it.hasNext();) {
                        List l = (List) it.next();
                        insertExpandStatement.setObject(2, l.get(1)); //static
                        insertExpandStatement.setObject(3, l.get(0)); //expand
                        insertExpandStatement.setObject(4, l.get(2)); //numNodes
                        insertExpandStatement.execute();
                    }
                }
            } catch(SQLException e1) {
                e1.printStackTrace();
                conn = null;
                insertStatement = null;
                insertExpandStatement = null;
            }
        } else {
            savedResults.add(currentTest);
            Profiler.printResults();
        }
    }

    /**
     * the key can be one of the constants or any other string. But only the
     * constants get written into the database.
     *
     * @param key DOCUMENT ME!
     * @param value DOCUMENT ME!
     */
    public static void set(String key, Object value) {
        if(!active) {
            return;
        }

        currentTest.set(key, value);
    }

    /**
     * can be called to notify the start of some operation.
     *
     * @param identifier indentifies the operation to measure.
     */
    public static void start(String identifier) {
        if(!active) {
            return; //no profiling wished
        }

        currentTest.startTimes.put(identifier,
            new Long(System.currentTimeMillis()));
    }

    /**
     * should be called after the measured operation. It computed the time that
     * has ellapsed since the last "start" call with the same identifier.
     * multiple start-stop calls get NOT accumulated.
     *
     * @param identifier DOCUMENT ME!
     */
    public static void stop(String identifier) {
        if(!active) {
            return; //no profiling wished
        }

        long stop = System.currentTimeMillis();
        Long start = (Long) currentTest.startTimes.remove(identifier);
        assert start != null;

        long diff = stop - start.longValue();

        currentTest.values.put(identifier, new Long(diff));
    }

    /**
     * is called after a single expand operation.
     *
     * @param numNodesCurrent the number of nodes contained in the expanded view.
     */
    public static void transferExpandTimes(int numNodesCurrent) {
        Long valueExpand = (Long) currentTest.values.remove(TIME_SINGLE_EXPAND);
        Long valueStatic = (Long) currentTest.values.remove(TIME_SINGLE_STATIC);
        currentTest.addExpandTime(valueExpand, valueStatic,
            new Long(numNodesCurrent));
    }

    /**
     *
     */
    public static void turnOnDatabase() {
        database_on = true;
        try {
            Class.forName("org.postgresql.Driver");
            ensureConnection();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            database_on = false;
            conn = null;
        } catch(SQLException e) {
            e.printStackTrace();
            database_on = false;
            conn = null;
        }
    }

    /**
     * tries to establish a connection
     *
     * @throws SQLException DOCUMENT ME!
     */
    private static void ensureConnection()
        throws SQLException {
        if(conn == null) {
            String password = "faoveeC4"; //PasswordChooser.showDialog(null);
            if(password != null) {
                conn =
                    DriverManager.getConnection("jdbc:postgresql://lion.fmi.uni-passau.de",
                        "proepste", password);
            } else {
                database_on = false;
                throw new SQLException("no password provided");
            }

            insertStatement = null;
            insertExpandStatement = null;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    private static void ensureInsertStats()
        throws SQLException {
        if(conn != null && insertStatement == null) {
            //the complicated table has an additional column "serial", but the
            //needed statement is luckily the same 
            String sql =
                "insert into " + RunTests.getTableNameToWrite()
                + "  values (current_timestamp";
            for(int i = 1; i < tableColumns.length; i++) {
                sql += ",?";
            }

            sql += ")";
            insertStatement = conn.prepareStatement(sql);
            if(RunTests.singleExpandMode) {
                sql = "insert into myschema.expand_times values ((select run_id from "
                    + RunTests.getTableNameToWrite()
                    + "where seed=? order by time_stamp DESC LIMIT 1 ),"
                    + " ?,?,?)";
                insertExpandStatement = conn.prepareStatement(sql);
            }
        } else if(conn == null && insertStatement != null) {
            assert !database_on;
            insertStatement = null;
            insertExpandStatement = null;
        }
    }

    /**
     * fills a parameter in the statement, if no value is available, sets null.
     *
     * @param ps the statement to fill
     * @param key the identifier of the parameter
     * @param pos the position of the parameter in the preparedstatement
     *
     * @throws SQLException DOCUMENT ME!
     */
    private static void fillParameter(PreparedStatement ps, String key, int pos)
        throws SQLException {
        if(currentTest.values.containsKey(key)) {
            ps.setObject(pos, (currentTest.values.get(key)));
        } else {
            int type = ps.getParameterMetaData().getParameterType(pos);
            ps.setNull(pos, type);
        }
    }

    //~ Inner Classes ==========================================================

    /**
     * contains all values of a test run.
     */
    public static class TestResult {
        //     static HashMap otherValues = new HashMap();
        HashMap startTimes = new HashMap(100);
        HashMap values = new HashMap(100);
        List timeValues = new LinkedList();

        /**
         * Creates a new TestResult object.
         */
        public TestResult() {}

        /**
         * DOCUMENT ME!
         *
         * @param expTime DOCUMENT ME!
         * @param staticTime DOCUMENT ME!
         * @param numNodesCurrent DOCUMENT ME!
         */
        public void addExpandTime(Long expTime, Long staticTime,
            Long numNodesCurrent) {
            List l = new LinkedList();
            l.add(expTime);
            l.add(staticTime);
            l.add(numNodesCurrent);
            timeValues.add(l);
        }

        /**
         * DOCUMENT ME!
         *
         * @param key DOCUMENT ME!
         * @param value DOCUMENT ME!
         */
        public void set(String key, Object value) {
            values.put(key, value);
        }

        /**
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return "Testresult: " + values + "\n";
        }
    }

    /**
     *
     */

    //    private static class PasswordChooser extends JPanel {
    //        private JLabel portLabel;
    //        private JPasswordField portField;
    //
    //        /**
    //         * Creates a new PasswordChooser object.
    //         */
    //        private PasswordChooser() {
    //            portLabel = new JLabel("input password for database");
    //            portField = new JPasswordField(10);
    //
    //            JPanel jPanel1 = new JPanel();
    //            JPanel jPanel2 = new JPanel();
    //            jPanel1.add(portLabel);
    //            jPanel1.add(portField);
    //            add(jPanel1);
    //        }
    //
    //        /**
    //         * DOCUMENT ME!
    //         *
    //         * @param parent DOCUMENT ME!
    //         *
    //         * @return DOCUMENT ME!
    //         */
    //        public static String showDialog(Component parent) {
    //            PasswordChooser chooser = new PasswordChooser();
    //            int result =
    //                JOptionPane.showConfirmDialog(parent, chooser, "",
    //                    JOptionPane.OK_CANCEL_OPTION);
    //            if(result == JOptionPane.OK_OPTION) {
    //                return chooser.portField.getText();
    //            } else {
    //                return null;
    //            }
    //        }
    //    }
}
