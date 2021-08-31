package com.leaderli.liutil.event;

import java.util.EventObject;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LiEventStore {

    private final LiEventMap liEventMap = new LiEventMap();


    public void registerListener(ILiEventListener listener) {
        //noinspection unchecked
        liEventMap.put(listener.listenType(), listener);
    }

    public void unRegisterListener(ILiEventListener listener) {
        List list = liEventMap.get(listener.listenType());
        if (list != null) {
            list.remove(listener);
        }
    }

    public <T extends EventObject> void push(T liEvent) {
        Class<T> cls = (Class<T>) liEvent.getClass();
        liEventMap.get(cls).forEach(listener -> listener.listen(liEvent));
    }

}
