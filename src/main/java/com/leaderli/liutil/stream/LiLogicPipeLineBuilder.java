package com.leaderli.liutil.stream;

public class LiLogicPipeLineBuilder<T> {

   protected LiSink<T, Boolean> sink;

//    public LiLogicPipeLineBuilder() {
//        if(sink==null){
//            this.sink = new LiSink<T, Boolean>(null) {
//                @Override
//                public Boolean apply(T t, Boolean last) {
//                    return this.next.apply(t,null);
//                }
//
//            };
//        }
//        this.sink = sink;
//    }
}
