package com.leaderli.liutil.stream;

import org.junit.Test;


public class LiSinkTest {

    @Test
    public void test() {

        LiSink<String,Boolean> begin= new LiSink<String,Boolean>(null){
            @Override
            public Boolean apply(String o) {

                System.out.println("1:"+o);

                return  next.apply(o);
            }


        };
        LiSink<String,Boolean> one = new LiSink<String,Boolean>(begin){
            @Override
            public Boolean apply(String o) {

                System.out.println("2:"+o);

                return next.apply(o);
            }
        };
        LiSink<String,Boolean> two= new LiSink<String,Boolean>(one){
            @Override
            public Boolean apply(String o) {

                System.out.println("3:"+o);

                return next.apply(o);
            }
        };
        LiSink<String,Boolean> end= new LiSink<String,Boolean>(two){
            @Override
            public Boolean apply(String o) {

                System.out.println("4:"+o);

                return  true;
            }
        };

        System.out.println(end.request("hello"));

    }


}