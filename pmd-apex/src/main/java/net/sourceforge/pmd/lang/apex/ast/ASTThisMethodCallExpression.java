/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.ast;

import apex.jorje.semantic.ast.expression.ThisMethodCallExpression;

public final class ASTThisMethodCallExpression extends AbstractApexNode<ThisMethodCallExpression> {

    ASTThisMethodCallExpression(ThisMethodCallExpression thisMethodCallExpression) {
        super(thisMethodCallExpression);
    }

    @Override
    public Object jjtAccept(ApexParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
