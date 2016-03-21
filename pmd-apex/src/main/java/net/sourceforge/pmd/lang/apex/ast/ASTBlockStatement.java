/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package net.sourceforge.pmd.lang.apex.ast;

import apex.jorje.semantic.ast.statement.BlockStatement;

public class ASTBlockStatement extends AbstractApexNode<BlockStatement> {

	public ASTBlockStatement(BlockStatement blockStatement) {
		super(blockStatement);
	}
}
