/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.scala.ast;

import scala.meta.Pkg;

/**
 * The ASTPkgObject node implementation.
 */
public final class ASTPkgObject extends AbstractScalaNode<Pkg.Object> {

    ASTPkgObject(Pkg.Object scalaNode) {
        super(scalaNode);
    }

    @Override
    public <D, R> R accept(ScalaParserVisitor<D, R> visitor, D data) {
        return visitor.visit(this, data);
    }
}
