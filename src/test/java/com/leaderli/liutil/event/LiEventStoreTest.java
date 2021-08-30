package com.leaderli.liutil.event;

import org.junit.Test;

public class LiEventStoreTest {


    private static class TestLiEvent extends LiEvent<String> {

        public TestLiEvent(String source) {
            super(source);
        }
    }


    private static class TestLiEventListener implements ILiEventListener<TestLiEvent> {


        @Override
        public void listen(TestLiEvent event) {
            System.out.println("listen:" + event.getSource());

        }

        @Override
        public Class<TestLiEvent> listenType() {
            return TestLiEvent.class;
        }
    }


    private static class TestLiEventListener2 implements ILiEventListener<TestLiEvent> {


        @Override
        public void listen(TestLiEvent event) {
            System.out.println("listen2:" + event.getSource());

        }

        @Override
        public Class<TestLiEvent> listenType() {
            return TestLiEvent.class;
        }
    }

    private static class TestListenerLi implements ILiEventListener<String> {

        @Override
        public void listen(String event) {

            System.out.println("->" + event);
        }

        @Override
        public Class<String> listenType() {
            return String.class;
        }
    }


    @Test
    public void getPublisher() {

        TestLiEvent event = new TestLiEvent("hello");


        LiEventStore eventStore = new LiEventStore();

        eventStore.registerListener(new TestLiEventListener());
        eventStore.registerListener(new TestLiEventListener2());

        eventStore.push(new TestLiEvent("123"));

    }


}