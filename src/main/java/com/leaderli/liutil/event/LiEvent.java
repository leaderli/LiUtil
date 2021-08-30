package com.leaderli.liutil.event;


import java.util.Objects;

public class LiEvent<T> {

    protected transient  T source;


    public LiEvent(T source) {

        Objects.requireNonNull(source,"source must not be null");

        this.source = source;
    }

    public T getSource() {
        return source;
    }
}
