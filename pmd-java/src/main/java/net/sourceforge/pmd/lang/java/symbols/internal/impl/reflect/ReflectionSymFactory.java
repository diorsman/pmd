/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.symbols.internal.impl.reflect;


import static net.sourceforge.pmd.lang.java.symbols.internal.impl.reflect.ReflectedClassImpl.createOuterClass;
import static net.sourceforge.pmd.lang.java.symbols.internal.impl.reflect.ReflectedClassImpl.createWithEnclosing;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.sourceforge.pmd.lang.java.symbols.JClassSymbol;
import net.sourceforge.pmd.lang.java.symbols.internal.impl.SymbolFactory;
import net.sourceforge.pmd.lang.java.types.internal.impl.ReflectedTypeFactory;

/**
 * Builds symbols.
 *
 * <p>This may be improved later to eg cache and reuse the most recently
 * accessed symbols (there may be a lot of cache hits in a typical java file).
 */
public final class ReflectionSymFactory implements SymbolFactory<Class<?>> {

    private static volatile Map<Class<?>, JClassSymbol> COMMON_SYMBOLS;



    public ReflectionSymFactory() {

    }

    @Override
    public ReflectedTypeFactory types() {
        // lazy load to avoid class init cycle
        if (typeFactory == null) {
            synchronized (this) {
                if (typeFactory == null) {
                    typeFactory = new ReflectedTypeFactory(this);
                }
            }
        }
        return typeFactory;
    }


    @Override
    @Nullable
    public JClassSymbol getClassSymbol(@Nullable Class<?> klass) {
        if (klass == null) {
            return null;
        }

        Map<Class<?>, JClassSymbol> shared = getCommonSyms();
        if (shared.containsKey(klass)) {
            return shared.get(klass);
        }

        if (klass.getEnclosingClass() != null) {
            JClassSymbol enclosing = getClassSymbol(klass.getEnclosingClass());
            assert enclosing != null;
            return createWithEnclosing(this, enclosing, klass);
        }

        if (klass.isArray()) {
            JClassSymbol component = getClassSymbol(klass.getComponentType());
            return makeArraySymbol(component);
        }

        return createOuterClass(this, klass);
    }

    private Map<Class<?>, JClassSymbol> getCommonSyms() {
        Map<Class<?>, JClassSymbol> shared = COMMON_SYMBOLS;
        if (shared == null) {
            synchronized (ReflectionSymFactory.class) {
                shared = COMMON_SYMBOLS;
                if (shared == null) {
                    shared = initCommonSyms();
                    COMMON_SYMBOLS = shared;
                }
            }
        }
        return shared;
    }

    private static void putStr(Map<Class<?>, JClassSymbol> byClass,
                               Class<?> booleanClass,
                               JClassSymbol booleanSym) {
        byClass.put(booleanClass, booleanSym);
    }



    private Map<Class<?>, JClassSymbol> initCommonSyms() {
        // consider putting whole java.lang + java.util in there ?

        Map<Class<?>, JClassSymbol> specials = new HashMap<>();

        putStr(specials, Object.class, OBJECT_SYM);

        putStr(specials, boolean.class, BOOLEAN_SYM);
        putStr(specials, byte.class, BYTE_SYM);
        putStr(specials, char.class, CHAR_SYM);
        putStr(specials, double.class, DOUBLE_SYM);
        putStr(specials, float.class, FLOAT_SYM);
        putStr(specials, int.class, INT_SYM);
        putStr(specials, long.class, LONG_SYM);
        putStr(specials, short.class, SHORT_SYM);
        putStr(specials, void.class, VOID_SYM);

        putStr(specials, Cloneable.class, CLONEABLE_SYM);
        putStr(specials, Serializable.class, SERIALIZABLE_SYM);

        putStr(specials, Boolean.class, BOXED_BOOLEAN_SYM);
        putStr(specials, Byte.class, BOXED_BYTE_SYM);
        putStr(specials, Character.class, BOXED_CHAR_SYM);
        putStr(specials, Double.class, BOXED_DOUBLE_SYM);
        putStr(specials, Float.class, BOXED_FLOAT_SYM);
        putStr(specials, Integer.class, BOXED_INT_SYM);
        putStr(specials, Long.class, BOXED_LONG_SYM);
        putStr(specials, Short.class, BOXED_SHORT_SYM);
        putStr(specials, Void.class, BOXED_VOID_SYM);

        putStr(specials, Iterable.class, ITERABLE_SYM);
        putStr(specials, Enum.class, ENUM_SYM);
        putStr(specials, String.class, STRING_SYM);

        return Collections.unmodifiableMap(specials);
    }

}
