package com.leaderli.liutil.event;

public interface ILiEventPublisher<T extends  LiEvent> {

    void publish(T event);
}
