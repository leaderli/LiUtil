package com.leaderli.liutil.stream;

import java.util.function.Predicate;

public class LiLogicPipeLineCombineOperation<T> extends LiLogicPipeLineBuilder<T> implements LinterCombineOperation<T> {


    public LiLogicPipeLineCombineOperation(LiLogicPipeLine<T> liLogicPipeLine) {
        super(liLogicPipeLine);
    }

    @Override
    public LinterPredicate<T> test(Predicate<T> predicate) {


        liLogicPipeLine.add((sink, t, last) -> {


            boolean value = predicate.test(t);

            if (sink.next != null) {
                return sink.next.apply(t, value);
            }

            return value;
        });

        return new LiLogicPipeLinePredicate<>(liLogicPipeLine);
    }

    @Override
    public LinterOperation<T> not() {
        return null;
    }


}
