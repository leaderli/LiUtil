package com.leaderli.liutil.meta;

import org.junit.Test;

import static com.leaderli.liutil.meta.LiPrimitive.get;

public class LiPrimitiveTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void test() {

        assert get(int.class) == 0;
        assert get(Integer.class) == 0;
        assert get(char.class) == 0;
        assert get(Character.class) == 0;
        assert get(byte.class) == 0;
        assert get(Byte.class) == 0;
        assert get(long.class) == 0;
        assert get(Long.class) == 0;

        assert !get(boolean.class);
        assert !get(Boolean.class);

        assert get(double.class) == 0;
        assert get(Double.class) == 0;

        assert get(float.class) == 0;
        assert get(Float.class) == 0;

        assert get(short.class) == 0;
        assert get(Short.class) == 0;

        assert get(void.class) == null;
        assert get(Void.class) == null;

        assert get(String.class) == null;
    }

}
