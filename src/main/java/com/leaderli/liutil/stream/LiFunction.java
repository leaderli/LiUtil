package com.leaderli.liutil.stream;

public interface LiFunction<T, R> {

    R apply(T t, R last);
}
