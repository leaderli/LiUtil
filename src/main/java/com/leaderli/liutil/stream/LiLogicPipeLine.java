package com.leaderli.liutil.stream;

import java.util.function.Predicate;

public class LiLogicPipeLine<T> implements LinterLogicPipeLine<T>{


    private static class Head<T> extends LiSink<T, Boolean> {

        public Head() {
            super(null);
        }

        @Override
        public Boolean apply(T request, Boolean last) {
            return next(request, LiPredicateSink.NO_NOT_OPERATION);
        }
    }

    protected LiSink<T, Boolean> liSink = new Head<>();

    private LiLogicPipeLine() {

    }


    public static <T> LinterCombineOperation<T> instance() {

        return new LiLogicPipeLine<>();
    }

    @Override
    public Boolean apply(T t) {
        return liSink.request(t);
    }

    @Override
    public LinterOperation<T> not() {
        this.liSink = new LiSink<T, Boolean>(this.liSink) {
            @Override
            public Boolean apply(T request, Boolean last) {
                return next(request, LiPredicateSink.IS_NOT_OPERATION);
            }
        };
        return this;
    }

    @Override
    public LinterPredicate<T> test(Predicate<T> predicate) {

        this.liSink = new LiPredicateSink<>(this.liSink, predicate);

        return this;
    }


    @Override
    public LinterCombineOperation<T> and() {

        this.liSink = new LiSink<T, Boolean>(this.liSink) {
            @Override
            public Boolean apply(T request, Boolean lastPredicateResult) {
                //短路
                if (lastPredicateResult) {
                    return next(request, LiPredicateSink.NO_NOT_OPERATION);
                }
                return false;
            }
        };
        return this;
    }

    @Override
    public LinterCombineOperation<T> or() {
        this.liSink = new LiSink<T, Boolean>(this.liSink) {
            @Override
            public Boolean apply(T request, Boolean lastPredicateResult) {
                //短路
                if (lastPredicateResult) {
                    return true;
                }
                return next(request, LiPredicateSink.NO_NOT_OPERATION);
            }
        };
        return this;
    }

}
