package com.leaderli.liutil.stream;

import org.junit.Test;

import static org.junit.Assert.*;

public class LiLogicPipeLineTest {

    @Test
    public void test() {

        LinterCombineOperation<String> logic = LiLogicPipeLine.instance();

        assert logic.apply("hello");

        logic.test(str->false);
        assert !logic.apply("hello");



    }



}