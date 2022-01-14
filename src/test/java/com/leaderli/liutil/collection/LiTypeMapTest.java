package com.leaderli.liutil.collection;

import org.junit.Test;

public class LiTypeMapTest {


    @Test
    public void test() {

        LiTypeMap liTypeMap = new LiTypeMap();

        String value = liTypeMap.get(String.class);
        assert value == null;

        liTypeMap.put(String.class,"");
        value = liTypeMap.get(String.class);
        assert value != null;
    }

}
