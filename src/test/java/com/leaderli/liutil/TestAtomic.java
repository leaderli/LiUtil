package com.leaderli.liutil;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {

    @Test
    public  void test(){

        AtomicInteger at  = new AtomicInteger(10);

        System.out.println(at.compareAndSet(10, 11));
        System.out.println(at.compareAndSet(10, 10));

    }
}

