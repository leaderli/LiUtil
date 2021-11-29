package com.leaderli.liutil.stream;

public abstract class LiLogicPipeLine<T> extends LiSink<T, Boolean>  {


    public LiLogicPipeLine(LiSink<T, Boolean> prev) {
        super(prev);
    }


    public  LiLogicPipeLine<T> HEAD = new LiLogicPipeLine<T>(null) {
        @Override
        public Boolean apply(T t, Boolean last) {
            return next.apply(t,null);
        }
    };

}
