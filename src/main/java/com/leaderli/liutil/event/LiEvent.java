package com.leaderli.liutil.event;


import java.util.EventObject;

public class LiEvent<T> extends EventObject {


    public LiEvent(T source) {
        super(source);
    }

    @SuppressWarnings("unchecked")
    public T getSource() {
        return (T) source;
    }
}
