package com.leaderli.liutil.stream;

@Deprecated
public interface LiFunction<T, R> {

    R apply(T request, R last);
}
