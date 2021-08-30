package com.leaderli.liutil.event;

import java.util.*;

class LiEventMap {
    private final Map<Class<?>, List<ILiEventListener<?>>> eventListenerMap = new HashMap<>();

    public <T extends LiEvent<?>> void put(Class<T> cls, ILiEventListener<T> listener) {
        this.eventListenerMap.computeIfAbsent(cls, c -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecked")
    public <T> List<ILiEventListener<T>> get(Class<T> cls) {
        Object iLiEventListeners = this.eventListenerMap.computeIfAbsent(cls, c -> new ArrayList<>());
        return (List<ILiEventListener<T>>) iLiEventListeners;
    }
}
