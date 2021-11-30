package com.leaderli.liutil.stream;

import org.junit.Test;

import java.util.function.Predicate;

public class LiLogicPipeLineTest {

    @Test
    public void test() {


        assert !LiLogicPipeLine.instance().test(str -> true).and().test(str -> false).apply("");


        assert !LiLogicPipeLine.instance().test(str -> false).and().test(str -> false).apply("hello");


        assert !LiLogicPipeLine.instance().test(str -> false).and().test(str -> true).apply("hello");


        assert LiLogicPipeLine.instance().test(str -> true).and().test(str -> true).apply("hello");


        assert LiLogicPipeLine.instance().test(str -> true).or().test(str -> false).apply("hello");


        assert !LiLogicPipeLine.instance().test(str -> false).or().test(str -> false).apply("hello");


        assert LiLogicPipeLine.instance().test(str -> false).or().test(str -> true).apply("hello");


        assert LiLogicPipeLine.instance().test(str -> true).or().test(str -> true).apply("hello");


        assert LiLogicPipeLine.instance().test(str -> true).apply("hello");


        assert !LiLogicPipeLine.instance().test(str -> false).apply("hello");


        assert !LiLogicPipeLine.instance().not().test(str -> true).apply("hello");


        assert LiLogicPipeLine.instance().not().test(str -> false).apply("hello");


        LiLogicPipeLine.instance().test(str -> false);
        assert LiLogicPipeLine.instance().not().test(str -> false).and().not().test(str -> false).apply("hello");

    }


    private interface MyLinterPredicate extends LinterPredicate<String> {
        MyLinterCombineOperation and();

        MyLinterCombineOperation or();

    }

    private interface MyLinterOperation extends LinterOperation<String> {
        MyLinterPredicate len(int size);

        MyLinterPredicate test(Predicate<String> predicate);
    }

    private interface MyLinterNotOperation extends LinterNotOperation<String> {
        MyLinterOperation not();
    }

    private interface MyLinterCombineOperation extends MyLinterOperation, MyLinterNotOperation, LinterCombineOperation<String> {
    }


    private interface MyLinterLogicPipeLine extends MyLinterCombineOperation, MyLinterPredicate {

    }

    private static class MyLiLogicPipeLine implements MyLinterLogicPipeLine {

        private final LiLogicPipeLine<String> proxy = (LiLogicPipeLine<String>) LiLogicPipeLine.<String>instance();

        private MyLiLogicPipeLine() {

        }

        public static <T> MyLiLogicPipeLine instance() {

            return new MyLiLogicPipeLine();
        }

        @Override
        public MyLinterCombineOperation and() {
            proxy.and();
            return this;
        }

        @Override
        public MyLinterCombineOperation or() {
            proxy.or();
            return this;
        }

        @Override
        public MyLinterOperation not() {
            proxy.not();
            return this;
        }

        @Override
        public MyLinterPredicate test(Predicate<String> predicate) {
            proxy.test(predicate);
            return this;
        }

        @Override
        public Boolean apply(String s) {
            return proxy.apply(s);
        }

        @Override
        public MyLinterPredicate len(int size) {
            test(str -> size == str.length());
            return this;
        }


    }


    @Test
    public void test2() throws Throwable {
        MyLinterCombineOperation operation;
//        operation.
        MyLinterCombineOperation logic = new MyLiLogicPipeLine();
        System.out.println(new MyLiLogicPipeLine().test(str -> true).and().test(str -> false).apply("1"));

        System.out.println(new MyLiLogicPipeLine().len(1).or().len(2).apply("1"));
        System.out.println(new MyLiLogicPipeLine().len(1).and().len(2).apply("1"));
//        logic

    }
}
