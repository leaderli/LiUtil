package com.leaderli.liutil.util;

import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class LiClassUtilTest {


    @Test
    public void testPrimitiveToWrapper() {

        assert LiClassUtil.primitiveToWrapper(int.class) == Integer.class;
        assert LiClassUtil.primitiveToWrapper(void.class) == Void.class;
        assert LiClassUtil.primitiveToWrapper(String.class) == String.class;
        assert LiClassUtil.primitiveToWrapper(null) == null;
    }

    @Test
    public void testIsAssignableFromOrIsWrapper() {

        assert !LiClassUtil.isAssignableFromOrIsWrapper(null, null);
        assert !LiClassUtil.isAssignableFromOrIsWrapper(null, String.class);
        assert !LiClassUtil.isAssignableFromOrIsWrapper(String.class, null);
        assert LiClassUtil.isAssignableFromOrIsWrapper(int.class, Integer.class);
        assert LiClassUtil.isAssignableFromOrIsWrapper(Integer.class, int.class);
        assert LiClassUtil.isAssignableFromOrIsWrapper(CharSequence.class, String.class);
        assert !LiClassUtil.isAssignableFromOrIsWrapper(String.class, CharSequence.class);

    }
}
