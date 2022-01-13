package com.leaderli.liutil.stream;

import org.junit.Test;


public class LiSinkTest {

    @Test
    public void test() {

        LiSink<String, Boolean> prev = null;
        for (int i = 0; i < 1000; i++) {

            int finalI = i;
            LiSink<String, Boolean> next = new LiSink<String, Boolean>(prev) {
                @Override
                public Boolean apply(String request, Boolean last) {

                    System.out.println(finalI +":" + request +" last:"+last);


                    if(this.next!=null){

                        return this.next.apply(request,last);
                    }
                    return false;
                }
            };
            prev =next;
        }


        boolean hello = prev.request("hello");
        System.out.println(hello);




    }



}
