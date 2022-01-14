package com.leaderli.liutil.meta;

import com.leaderli.liutil.util.LiClassUtil;

import java.util.HashMap;
import java.util.Map;

public class LiPrimitive {


    public static final int INT = Primitive.INT;
    public static final char CHAR = Primitive.CHAR;
    public static final byte BYTE = Primitive.BYTE;
    public static final long LONG = Primitive.LONG;
    public static final boolean BOOLEAN = Primitive.BOOLEAN;
    public static final double DOUBLE = Primitive.DOUBLE;
    public static final float FLOAT = Primitive.FLOAT;
    public static final short SHORT = Primitive.SHORT;
    private static final Map<Class<?>, Object> ZERO_VALUE = new HashMap<>();

    static {
        ZERO_VALUE.put(Boolean.class, BOOLEAN);
        ZERO_VALUE.put(Byte.class, BYTE);
        ZERO_VALUE.put(Character.class, CHAR);
        ZERO_VALUE.put(Short.class, SHORT);
        ZERO_VALUE.put(Integer.class, INT);
        ZERO_VALUE.put(Long.class, LONG);
        ZERO_VALUE.put(Double.class, DOUBLE);
        ZERO_VALUE.put(Float.class, FLOAT);
        ZERO_VALUE.put(Void.class, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> type) {
        type = (Class<T>) LiClassUtil.primitiveToWrapper(type);
        return (T) ZERO_VALUE.get(type);
    }


    private static class Primitive {
        public static int INT;

        public static char CHAR;
        public static byte BYTE;
        public static long LONG;
        public static boolean BOOLEAN;
        public static double DOUBLE;
        public static float FLOAT;
        public static short SHORT;
    }


}
