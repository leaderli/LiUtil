package com.leaderli.liutil.event;

public interface ILiEventListener<T extends LiEvent> {

    void listen(T event);
}
