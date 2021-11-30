package com.leaderli.liutil.stream;

public class LiLogicPipeLinePredicate<T> extends LiLogicPipeLineBuilder<T> implements LinterPredicate<T> {


    public LiLogicPipeLinePredicate(LiLogicPipeLine<T> liLogicPipeLine) {
        super(liLogicPipeLine);
    }


    @Override
    public LinterCombineOperation<T> and() {
        return null;
    }

    @Override
    public LinterCombineOperation or() {
        return null;
    }


}
