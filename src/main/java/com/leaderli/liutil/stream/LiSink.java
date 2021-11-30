package com.leaderli.liutil.stream;

public abstract class LiSink<T, R> implements LiFunction<T, R> {

    public final LiSink<T, R> prev;
    public LiSink<T, R> next;


    public LiSink(LiSink<T, R> prev) {
        this.prev = prev;
        if (prev != null) {
            prev.next = this;
        }

    }


    public final R next(T request,R lastValue){
        if(this.next!=null){
            return this.next.apply(request, lastValue);
        }
        return lastValue;
    }


    public final R request(T request) {

        LiSink<T, R> prev = this;
        while (prev.prev != null) {
            prev = prev.prev;
        }
        return prev.apply(request, null);
    }

}
