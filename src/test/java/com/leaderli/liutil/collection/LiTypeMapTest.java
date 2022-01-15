package com.leaderli.liutil.collection;

import org.junit.Assert;
import org.junit.Test;

public class LiTypeMapTest {


    @Test
    public void test() {

        LiTypeMap liTypeMap = new LiTypeMap();

        String value = liTypeMap.get(String.class).get();


        Assert.assertNull(value);

        liTypeMap.put(String.class, "");
        value = liTypeMap.get(String.class).get();
        Assert.assertNotNull(value);
    }

    @Test
    public void computeIfAbsent() {
        LiTypeMap liTypeMap = new LiTypeMap();

        String value = liTypeMap.computeIfAbsent(String.class, () -> "123").get();
        Assert.assertEquals("123",value);
    }

    @Test
    public void getMono() {
        LiTypeMap liTypeMap = new LiTypeMap();

        Assert.assertTrue(liTypeMap.get(String.class).notPresent());
        liTypeMap.put(String.class, "");
        Assert.assertTrue(liTypeMap.get(String.class).isPresent());
    }
}
