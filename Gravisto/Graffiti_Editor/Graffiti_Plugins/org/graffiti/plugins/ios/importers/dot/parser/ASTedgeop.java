/* Generated By:JJTree: Do not edit this line. ASTedgeop.java */

package org.graffiti.plugins.ios.importers.dot.parser;

public class ASTedgeop extends SimpleNode {
    public ASTedgeop(int id) {
        super(id);
    }

    public ASTedgeop(DOTParser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. **/
    @Override
    public Object jjtAccept(DOTParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}