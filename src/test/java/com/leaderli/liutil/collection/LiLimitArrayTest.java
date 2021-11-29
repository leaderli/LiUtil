package com.leaderli.liutil.collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LiLimitArrayTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test() {
        thrown.expect(NegativeArraySizeException.class);
        new LiLimitArray<>(-1);

    }

    @Test
    public void test1() {
        LiLimitArray<Integer> limitArray = new LiLimitArray<>(10);

        limitArray.add(1);
        limitArray.add(2);

        assert limitArray.contains(1);
        assert limitArray.contains(2);

        limitArray.remove(2);

        assert limitArray.contains(1);
        assert !limitArray.contains(2);

        for (int i = 0; i < 10; i++) {
            limitArray.add(i);
        }


        for (int i = 0; i < 10; i++) {
            limitArray.contains(i);
        }

        limitArray.add(4);
        limitArray.remove(4);
        limitArray.remove(3);
        assert !limitArray.contains(3);
        assert !limitArray.contains(4);

    }

    private static class Limit{
       private int index;

       private int[] arr = new int[100];
    }
    @Test
    public void test2() {





    }


}