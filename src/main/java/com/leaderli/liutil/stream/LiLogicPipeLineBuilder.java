package com.leaderli.liutil.stream;

import java.util.function.Function;

public class LiLogicPipeLineBuilder<T> implements Function<T,Boolean> {

   protected final LiLogicPipeLine<T> liLogicPipeLine;



    public LiLogicPipeLineBuilder(LiLogicPipeLine<T> liLogicPipeLine) {
        this.liLogicPipeLine = liLogicPipeLine;
    }


    @Override
    public Boolean apply(T t) {
        return this.liLogicPipeLine.apply(t);
    }
}
