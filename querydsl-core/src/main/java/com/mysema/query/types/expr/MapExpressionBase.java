/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.types.expr;

import java.util.Map;

import javax.annotation.Nullable;

import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MapExpression;
import com.mysema.query.types.Ops;

/**
 * MapExpressionBase is an abstract base class for MapExpression implementations
 *
 * @author tiwe
 *
 * @param <K> key type
 * @param <V> value type
 */
public abstract class MapExpressionBase<K, V, Q extends SimpleExpression<? super V>> extends SimpleExpression<Map<K,V>> implements MapExpression<K,V> {

    private static final long serialVersionUID = 2856001983312366841L;

    @Nullable
    private volatile NumberExpression<Integer> size;

    @Nullable
    private volatile BooleanExpression empty;

    public MapExpressionBase(Class<? extends Map<K, V>> type) {
        super(type);
    }

    public final BooleanExpression contains(K key, V value) {
        return get(key).eq(value);
    }

    @SuppressWarnings("unchecked")
    public final BooleanExpression contains(Expression<K> key, Expression<V> value) {
        return get(key).eq((Expression)value);
    }

    public final BooleanExpression containsKey(Expression<K> key) {
        return BooleanOperation.create(Ops.CONTAINS_KEY, this, key);
    }

    public final BooleanExpression containsKey(K key) {
        return BooleanOperation.create(Ops.CONTAINS_KEY, this, new ConstantImpl<K>(key));
    }

    public final BooleanExpression containsValue(Expression<V> value) {
        return BooleanOperation.create(Ops.CONTAINS_VALUE, this, value);
    }

    public final BooleanExpression containsValue(V value) {
        return BooleanOperation.create(Ops.CONTAINS_VALUE, this, new ConstantImpl<V>(value));
    }

    public abstract Q get(Expression<K> key);

    public abstract Q get(K key);

    public final BooleanExpression isEmpty() {
        if (empty == null) {
            empty = BooleanOperation.create(Ops.MAP_IS_EMPTY, this);
        }
        return empty;
    }

    public final BooleanExpression isNotEmpty() {
        return isEmpty().not();
    }

    public final NumberExpression<Integer> size() {
        if (size == null) {
            size = NumberOperation.create(Integer.class, Ops.MAP_SIZE, this);
        }
        return size;
    }

}
