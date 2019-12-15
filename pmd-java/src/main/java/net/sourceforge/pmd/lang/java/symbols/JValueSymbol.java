/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */


package net.sourceforge.pmd.lang.java.symbols;

import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;


/**
 * Reference to a value, ie {@linkplain JLocalVariableSymbol local variable} or {@linkplain JFieldSymbol field}.
 *
 * @author Clément Fournier
 * @since 7.0.0
 */
public interface JValueSymbol extends BoundToNode<ASTVariableDeclaratorId> {


    /**
     * Returns true if this is a field reference, in
     * which case it can be safely downcast to {@link JFieldSymbol}.
     */
    default boolean isField() {
        return this instanceof JFieldSymbol;
    }

    /**
     * Returns true if this is a field reference, in
     * which case it can be safely downcast to {@link JFormalParamSymbol}.
     */
    default boolean isParameter() {
        return this instanceof JFormalParamSymbol;
    }


    /** Returns true if this declaration is declared final. */
    boolean isFinal();
}
