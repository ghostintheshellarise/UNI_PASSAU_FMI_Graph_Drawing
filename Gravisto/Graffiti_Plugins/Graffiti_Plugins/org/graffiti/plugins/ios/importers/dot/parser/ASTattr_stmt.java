/* Generated By:JJTree: Do not edit this line. ASTattr_stmt.java */

package org.graffiti.plugins.ios.importers.dot.parser;

public class ASTattr_stmt extends SimpleNode {
    public ASTattr_stmt(int id) {
        super(id);
    }

    public ASTattr_stmt(DOTParser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. **/
    @Override
    public Object jjtAccept(DOTParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
