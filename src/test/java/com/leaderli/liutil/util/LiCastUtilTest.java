package com.leaderli.liutil.util;

import org.junit.Test;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public class LiCastUtilTest {

    @Test
    public void cast() {

        Object a = "123";
        assert "123".equals(LiCastUtil.cast(a, String.class));
        assert LiCastUtil.cast(a, int.class) == null;

        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add("2");
        list.add(3);
        a = list;

        assert LiCastUtil.cast(a, Integer.class) == null;
        assert LiCastUtil.cast(list, Integer.class).size() == 2;
        assert LiCastUtil.cast(a, int.class) == null;
    }
    @Test
    public void test1() {



    }

    @Test
    public void primitiveToWrapper() {
        assert LiClassUtil.primitiveToWrapper(null)==null;
    }

    @Test
    public void testCast() {
        Object a = 1;
        assert LiCastUtil.cast(a, Integer.class) == 1;
        assert LiCastUtil.cast(a, int.class) == 1;

    }
}
