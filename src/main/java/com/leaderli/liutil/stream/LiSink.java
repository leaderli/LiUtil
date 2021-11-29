package com.leaderli.liutil.stream;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class LiSink<T,R> implements Function<T,R> {

    public final LiSink<T,R> prev;
    public LiSink<T,R> next;


    public LiSink(LiSink<T,R> prev) {
        this.prev = prev;
        if (prev != null) {
            prev.next = this;
        }

    }

    public final  R request(T request) {
        if (this.prev == null) {
            return this.apply(request);
        } else {
            return this.prev.request(request);
        }
    }

}
