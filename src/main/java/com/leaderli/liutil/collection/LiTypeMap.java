package com.leaderli.liutil.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class TypeValue<T> {


    public final Class<T> type;
    public final T value;

    TypeValue(Class<T> type, T value) {
        this.type = type;
        this.value = value;
    }
}

public class LiTypeMap {
    private final List<TypeValue<?>> proxy = new ArrayList<>();

    public <T> void put(Class<T> type, T value) {
        proxy.add(new TypeValue<>(type, value));
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<TypeValue<T>> get(Class<T> type) {
        return proxy.stream().filter(typeValue -> typeValue.type == type).map(typeValue -> (TypeValue<T>) typeValue).findAny();

    }
}
