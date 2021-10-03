package com.leaderli.liutil.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class LiMapUtilTest {

    @Test
    public void test() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("1", "1");
        map1.put("2", "1");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("1", "10");
        map2.put("3", "3");

        System.out.println(map1);
        Map override = LiMapUtil.override(map1, map2);
        System.out.println(override);
    }


}