package com.leaderli.liutil.collection;

import org.junit.Assert;
import org.junit.Test;

public class LiTypeMapTest {


    @Test
    public void test() {

        LiTypeMap liTypeMap = new LiTypeMap();

        String value = liTypeMap.get(String.class);


        Assert.assertNull(value);

        liTypeMap.put(String.class, "");
        value = liTypeMap.get(String.class);
        Assert.assertNotNull(value);
    }

}
