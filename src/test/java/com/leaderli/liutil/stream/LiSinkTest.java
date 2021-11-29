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
                public Boolean apply(String o,Boolean last) {

                    System.out.println(finalI +":" + o+" last:"+last);


                    if(this.next!=null){

                        return this.next.apply(o,last);
                    }
                    return false;
                }
            };
            prev =next;
        }


        boolean hello = prev.request("hello");
        System.out.println(hello);




    }

    @Test
    public void test11() throws Throwable{
        Boolean a = null;

        System.out.println(!a);
        System.out.println(a);

    }


}
