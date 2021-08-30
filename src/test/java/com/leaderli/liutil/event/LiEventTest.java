package com.leaderli.liutil.event;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LiEventTest {


    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void test(){

        thrown.expect(NullPointerException.class);
        thrown.expectMessage("source must not be null");
        new LiEvent<>(null);

    }

}