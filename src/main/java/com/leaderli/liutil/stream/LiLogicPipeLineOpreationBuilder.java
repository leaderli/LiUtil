package com.leaderli.liutil.stream;

import java.util.function.Predicate;

public class LiLogicPipeLineOpreationBuilder<T> {

    public LiLogicPipeLinePredictBuilder test(Predicate<T> predicate) {

        return new LiLogicPipeLinePredictBuilder(predicate);
    }

}
