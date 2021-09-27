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
        System.out.println(limitArray);
        limitArray.add(2);
        System.out.println(limitArray);
        limitArray.remove(2);
        System.out.println(limitArray);
        for (int i = 0; i < 10; i++) {
            limitArray.add(i);
            System.out.println(limitArray);
        }
        limitArray.remove(4);
        System.out.println(limitArray);
        limitArray.remove(3);
        System.out.println(limitArray);

    }

}