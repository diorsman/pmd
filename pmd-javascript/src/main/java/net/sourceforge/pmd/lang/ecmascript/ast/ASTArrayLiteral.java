/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.ast;

import org.mozilla.javascript.ast.ArrayLiteral;

public final class ASTArrayLiteral extends AbstractEcmascriptNode<ArrayLiteral>
        implements DestructuringNode, TrailingCommaNode {
    private boolean trailingComma;

    ASTArrayLiteral(ArrayLiteral arrayLiteral) {
        super(arrayLiteral);
    }

    @Override
    public Object jjtAccept(EcmascriptParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    @Override
    public boolean isDestructuring() {
        return node.isDestructuring();
    }

    @Override
    public boolean isTrailingComma() {
        return trailingComma;
    }

    @Override
    protected void setTrailingCommaExists(boolean b) {
        this.trailingComma = b;
    }
}
