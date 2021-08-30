package com.leaderli.liutil.event;

import com.leaderli.liutil.util.LiAssertUtil;
import com.leaderli.liutil.util.LiClassUtil;

import java.lang.reflect.Constructor;
import java.util.*;

public class LiEventStore implements ILiEventPublisher {


    private static class LiEventBridge<T extends LiEvent> implements ILiEventListener<T>, ILiEventPublisher<T> {

        private List<ILiEventListener<T>> listeners = new ArrayList<>();

        @Override
        public void listen(T event) {

            listeners.forEach(listener -> listener.listen(event));

        }

        void addListener(Class<? extends ILiEventListener<T>> listenerClass) {

            boolean notAdded = this.listeners.stream().allMatch(listener -> listener.getClass() != listenerClass);

            if (notAdded) {

                try {
                    Constructor<? extends ILiEventListener<T>> constructor = listenerClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    ILiEventListener<T> listener = constructor.newInstance();
                    this.listeners.add(listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void publish(T event) {
            listen(event);
        }
    }


    private Map<Class<? extends LiEvent>, LiEventBridge> event_type_listeners = new HashMap<>();

    private <T extends LiEvent> void putEventBridge(Class<T> cls, LiEventBridge<T> eventBridge) {
        event_type_listeners.put(cls, eventBridge);
    }

    private <T extends LiEvent> LiEventBridge<T> getEventBridge(Class<T> cls) {
        //noinspection unchecked
        LiEventBridge<T> liEventBridge = event_type_listeners.get(cls);
        if (liEventBridge == null) {
            liEventBridge = new LiEventBridge<>();
            putEventBridge(cls, liEventBridge);

        }
        return liEventBridge;

    }

    public <T extends LiEvent> ILiEventPublisher<T> getPublisher(Class<? extends ILiEventPublisher<T>> publisherClass) {

        Class directGenericInterfacesActualType = LiClassUtil.getDirectGenericInterfacesActualType(publisherClass, ILiEventPublisher.class);
        LiAssertUtil.assertTrue(LiEvent.class.isAssignableFrom(directGenericInterfacesActualType), directGenericInterfacesActualType.toString());
        //noinspection unchecked
        return getEventBridge(directGenericInterfacesActualType);

    }


    @Override
    public void publish(LiEvent event) {
//        noinspection unchecked
        event_type_listeners.get(event.getClass()).publish(event);
    }


    public <T extends LiEvent> void addListener(Class<? extends ILiEventListener<T>> listenerClass) {


        Class directGenericInterfacesActualType = LiClassUtil.getDirectGenericInterfacesActualType(listenerClass, ILiEventListener.class);

        if (!LiEvent.class.isAssignableFrom(directGenericInterfacesActualType)) {

            //若当前listener是继承了一个继承了ILiEventListener的泛型类
            directGenericInterfacesActualType = LiClassUtil.getDirectGenericSupperClassActualType(listenerClass);

            if (!LiEvent.class.isAssignableFrom(directGenericInterfacesActualType)) {

                //若当前listener是继承了一个继承了ILiEventListener的

                Class<?> superclass = listenerClass.getSuperclass();
                if (superclass != null) {

                    directGenericInterfacesActualType = LiClassUtil.getDirectGenericInterfacesActualType(superclass, ILiEventListener.class);
                }
                for (Class<?> cls : listenerClass.getInterfaces()) {

                    Class interfaceActualType = LiClassUtil.getDirectGenericInterfacesActualType(cls, ILiEventListener.class);

                    if (LiEvent.class.isAssignableFrom(interfaceActualType)) {
                        directGenericInterfacesActualType = interfaceActualType;
                    }

                }
            }

        }
        LiAssertUtil.assertTrue(LiEvent.class.isAssignableFrom(directGenericInterfacesActualType), directGenericInterfacesActualType.toString());
        //noinspection unchecked
        LiEventBridge<T> bridge = event_type_listeners.computeIfAbsent(
                directGenericInterfacesActualType,
                k -> new LiEventBridge<T>() {
                });
        bridge.addListener(listenerClass);


    }

    public static void main(String[] args) {
        System.out.println(Object.class.isAssignableFrom(String.class));
    }

}
