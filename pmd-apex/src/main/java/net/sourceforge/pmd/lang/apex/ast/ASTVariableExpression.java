/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.ast;

import apex.jorje.semantic.ast.expression.VariableExpression;

public final class ASTVariableExpression extends AbstractApexNode<VariableExpression> {

    ASTVariableExpression(VariableExpression variableExpression) {
        super(variableExpression);
    }

    @Override
    public Object jjtAccept(ApexParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    @Override
    public String getImage() {
        if (node.getIdentifier() != null) {
            return node.getIdentifier().getValue();
        }
        return null;
    }
}
