package com.leaderli.liutil.stream;

public interface LiFunctionWithLiSink<T, R> {

    R apply(LiSink<T,R> sink,T t, R last);
}
