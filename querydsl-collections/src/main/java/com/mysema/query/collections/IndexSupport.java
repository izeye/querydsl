/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.collections;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysema.query.collections.support.DefaultIndexSupport;
import com.mysema.query.grammar.JavaOps;
import com.mysema.query.grammar.types.Expr;
import com.mysema.query.grammar.types.Expr.EBoolean;

/**
 * IndexSupport enables the injection of indexed query source lookup into 
 * collection query instances
 *
 * @see DefaultIndexSupport
 *
 * @author tiwe
 * @version $Id$
 */
public interface IndexSupport {
    
    /**
     * Initialize the IndexSupport instance
     * NOTE : IndexSupport instances are stateful
     * 
     * @param exprToIt
     * @param ops
     * @param orderedSources
     * @param condition
     */
    void init(Map<Expr<?>,Iterable<?>> exprToIt, JavaOps ops, List<? extends Expr<?>> orderedSources, EBoolean condition);
    
    <A> Iterator<A> getIterator(Expr<A> expr);

    <A> Iterator<A> getIterator(Expr<A> expr, Object[] bindings);

}
