package com.leaderli.liutil.event;

import com.leaderli.liutil.exception.LiAssertException;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LiEventStoreTest {


    private static class TestLiEvent extends LiEvent<String> {

        public TestLiEvent(String source) {
            super(source);
        }
    }

    private static class TestLiEventPublisher implements ILiEventPublisher<TestLiEvent> {

        @Override
        public void publish(TestLiEvent event) {

        }
    }


    private static class TestLiEventListener implements ILiEventListener<TestLiEvent> {


        @Override
        public void listen(TestLiEvent event) {
            System.out.println("listen:" + event.getSource());

        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getPublisher() {

        TestLiEvent event = new TestLiEvent("hello");


        LiEventStore eventStore = new LiEventStore();
        eventStore.addListener(TestLiEventListener.class);
        eventStore.addListener(TestLiEventListener.class);



        ILiEventPublisher<TestLiEvent> publisher = eventStore.getPublisher(TestLiEventPublisher.class);

        publisher.publish(event);

        eventStore.publish(event);
    }


    private static abstract class BaseTestLiEventListener<T extends LiEvent> implements ILiEventListener<T> {



    }

    private static class TestBase extends BaseTestLiEventListener<TestLiEvent>{

        @Override
        public void listen(TestLiEvent event) {
            System.out.println("base:"+event.getSource());
        }
    }

    private static abstract class BaseTest2LiEventListener implements ILiEventListener<TestLiEvent> {



    }

    private static class TestBase2 extends BaseTest2LiEventListener {

        @Override
        public void listen(TestLiEvent event) {
            System.out.println("base2:"+event.getSource());
        }
    }

    private static interface BaseTest3LiEventListener extends ILiEventListener<TestLiEvent> {



    }

    private static class TestBase3 implements BaseTest3LiEventListener {

        @Override
        public void listen(TestLiEvent event) {
            System.out.println("base3:"+event.getSource());
        }
    }

    @Test
    public void test(){
        LiEventStore eventStore = new LiEventStore();

        eventStore.addListener(TestBase.class);
        eventStore.addListener(TestBase2.class);
        eventStore.addListener(TestBase3.class);


        eventStore.publish(new TestLiEvent("123"));

    }




}