package com.leaderli.liutil.event;


import java.util.EventObject;

public class LiEvent<T> extends EventObject {


    public LiEvent(T source) {
        super(source);
    }

    public T getSource() {
        //noinspection unchecked
        return (T) source;
    }
}
