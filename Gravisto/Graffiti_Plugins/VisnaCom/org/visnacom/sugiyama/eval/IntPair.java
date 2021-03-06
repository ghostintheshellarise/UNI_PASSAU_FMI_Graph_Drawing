/*==============================================================================
*
*   IntPair.java
*
*   @author Michael Proepster
*
*==============================================================================
* $Id: IntPair.java 907 2005-10-19 12:03:31Z raitner $
*/

package org.visnacom.sugiyama.eval;


/**
 * a pair of integer values
 */
public class IntPair {
    //~ Instance fields ========================================================

    /** the first int value */
    public int int1;

    /** the second int value */
    public int int2;

    //~ Constructors ===========================================================

    /**
     * Creates a new IntPair object.
     *
     * @param i1 DOCUMENT ME!
     * @param i2 DOCUMENT ME!
     */
    public IntPair(int i1, int i2) {
        int1 = i1;
        int2 = i2;
    }

    /**
     * Creates a new IntPair object.
     */
    public IntPair() {}

    //~ Methods ================================================================

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[" + int1 + "," + int2 + "]";
    }
}
