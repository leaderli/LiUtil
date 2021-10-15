package com.leaderli.liutil.event;

import com.leaderli.liutil.type.GenericType;

public interface ILiEventListener<T> extends GenericType<T> {

    void listen(T event);

    default boolean unRegisterListenerAfterListen() {
        return false;
    }

}
