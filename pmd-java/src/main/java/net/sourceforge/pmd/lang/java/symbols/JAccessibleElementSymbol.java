/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */


package net.sourceforge.pmd.lang.java.symbols;

import java.lang.reflect.Modifier;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Represents declarations having access modifiers common to {@link JFieldSymbol},
 * {@link JClassSymbol}, {@link JMethodSymbol}, and {@link JConstructorSymbol}.
 *
 * @author Clément Fournier
 * @since 7.0.0
 */
public interface JAccessibleElementSymbol extends JElementSymbol {

    /**
     * Returns the modifiers of the element represented by this symbol,
     * as decodable by the standard {@link Modifier} API.
     */
    int getModifiers();


    /**
     * Returns the class that directly encloses this declaration.
     * This is equivalent to {@link Class#getEnclosingClass()}.
     * Returns null if this is a top-level type declaration.
     *
     * <p>This is necessarily an already resolved symbol, because
     * 1. if it's obtained from reflection, then the enclosing class is available
     * 2. if it's obtained from an AST, then the enclosing class is in the same source file so we can
     * know about it
     */
    @Nullable
    JClassSymbol getEnclosingClass();


    @NonNull
    String getPackageName();
}
