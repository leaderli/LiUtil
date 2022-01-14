package com.leaderli.liutil.event;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LiEventStoreTest {


    private static class TestLiEvent extends LiEvent<String> {

        public TestLiEvent(String source) {
            super(source);
        }
    }


    private static class TestLiEventListener implements ILiEventListener<TestLiEvent> {


        @Override
        public void listen(TestLiEvent event) {
            assert event.getSource().equals("123");

        }

        @Override
        public Class<TestLiEvent> genericType() {
            return TestLiEvent.class;
        }
    }


    private static class TestLiEventListener2 implements ILiEventListener<TestLiEvent> {


        @Override
        public void listen(TestLiEvent event) {
            assert event.getSource().equals("123");

        }

        @Override
        public Class<TestLiEvent> genericType() {
            return TestLiEvent.class;
        }
    }

    private static class TestListenerLi implements ILiEventListener<String> {

        @Override
        public void listen(String event) {

            assert event.equals("123");
        }

        @Override
        public Class<String> genericType() {
            return String.class;
        }
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void getPublisher() {

        TestLiEvent event = new TestLiEvent("hello");


        LiEventStore eventStore = new LiEventStore();

        eventStore.registerListener(new TestLiEventListener());
        eventStore.registerListener(new TestLiEventListener2());

        eventStore.push(new TestLiEvent("123"));
        thrown.expect(NullPointerException.class);
        //noinspection ConstantConditions
        eventStore.push(null);

    }
    @Test
    public void test1() {

        LiEventStore liEventStore = new LiEventStore();

        ILiEventListener<TestLiEvent> listener = new ILiEventListener<TestLiEvent>() {


            @Override
            public Class<TestLiEvent> genericType() {
                return TestLiEvent.class;
            }

            @Override
            public void listen(TestLiEvent event) {
                assert  event.getSource().equals("123");

            }

            @Override
            public boolean unRegisterListenerAfterListen() {
                return true;
            }
        };
        liEventStore.registerListener(listener);

        liEventStore.push(new TestLiEvent("123"));
        liEventStore.push(new TestLiEvent("123"));

    }


}
