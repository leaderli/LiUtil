package com.leaderli.liutil.event;

public interface ILiEventListener<T> {

    void listen(T event);

    Class<T> listenType();
}
