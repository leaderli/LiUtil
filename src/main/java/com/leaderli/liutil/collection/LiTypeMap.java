package com.leaderli.liutil.collection;

import com.leaderli.liutil.util.LiClassUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * store value by it's type
 */
public class LiTypeMap {
    private final Map<Class<?>, Object> proxy = new HashMap<>();

    /**
     * @param type  map key, it's a generic type
     * @param value map value, it's a generic value
     * @param <T>   key and value generic type
     */
    public <T> void put(Class<T> type, T value) {
        proxy.put(LiClassUtil.primitiveToWrapper(type), value);
    }

    /**
     * @param type map key, it's a generic type
     * @param <T>  map value, it's a generic value
     * @return get value by type, it could be null
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type) {
        return (T) proxy.get(LiClassUtil.primitiveToWrapper(type));
    }
}
