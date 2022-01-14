package com.leaderli.liutil.stream;

import org.junit.Test;


public class LiSinkTest {

    @Test
    public void test() {

        LiSink<String, Boolean> prev = null;
        for (int i = 0; i < 1000; i++) {

            prev = new LiSink<String, Boolean>(prev) {
                @Override
                public Boolean apply(String request, Boolean last) {


                    if(this.next!=null){

                        return this.next.apply(request,last);
                    }
                    return false;
                }
            };
        }


        boolean hello = prev.request("hello");
        assert !hello;





    }



}
