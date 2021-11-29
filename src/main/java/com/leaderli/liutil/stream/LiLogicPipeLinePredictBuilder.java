package com.leaderli.liutil.stream;

import java.util.function.Predicate;

public class LiLogicPipeLinePredictBuilder<T> extends LiLogicPipeLineBuilder<T> {


    public <T> LiLogicPipeLinePredictBuilder(Predicate<T> predicate) {
        new LiSink<T, Boolean>((LiSink<T, Boolean>) super.sink) {
            @Override
            public Boolean apply(T t, Boolean last) {
                return null;
            }
        };
    }

    public LiLogicPipeLineOpreationBuilder<T> and() {

        return new LiLogicPipeLineOpreationBuilder<T>();
    }

}
