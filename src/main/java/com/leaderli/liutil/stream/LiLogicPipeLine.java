package com.leaderli.liutil.stream;

import java.util.function.Function;
import java.util.function.Predicate;

public class LiLogicPipeLine<T> implements Function<T,Boolean> {


    private LiSink<T,Boolean> liSink ;
//    = new LiSink<T, Boolean>(null) {
//        @Override
//        public Boolean apply(T t, Boolean last) {
//
//            if(this.next!=null){
//                return next.apply(t,true);
//            }
//            return true;
//        }
//    };

    private LiLogicPipeLine(){
        add((sink,t,last)->{
            if(sink.next!=null){
                return sink.next.apply(t,true);
            }
            return true;
        });
    }


//    public static
public static <T>LinterCombineOperation<T> instance() {

    LiLogicPipeLine<T> logic = new LiLogicPipeLine<>();
    return new LiLogicPipeLineCombineOperation<>(logic);

}




    public  final void add(LiFunctionWithLiSink<T,Boolean> proxy){


        this.liSink = new LiSink<T,Boolean>(this.liSink){

            @Override
            public Boolean apply(T t, Boolean last) {
                return proxy.apply(this,t,last);
            }
        };

    }


    @Override
    public Boolean apply(T t) {
        return liSink.request(t);
    }


//
//    @Override
//    public LinterPredicate<T> test(Predicate<T> predicate) {
//        return  new LiLogicPipeLineCombineOperation<>(this).test(predicate);
//    }
//
//    @Override
//    public LinterOperation <T> not() {
//        return  new LiLogicPipeLineCombineOperation<>(this).not();
//    }

}
