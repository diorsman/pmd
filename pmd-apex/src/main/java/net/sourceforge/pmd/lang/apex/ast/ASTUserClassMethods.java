/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.ast;

import apex.jorje.semantic.ast.compilation.UserClassMethods;

public final class ASTUserClassMethods extends AbstractApexNode<UserClassMethods> {

    ASTUserClassMethods(UserClassMethods userClassMethods) {
        super(userClassMethods);
    }

    @Override
    public Object jjtAccept(ApexParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
