package com.leaderli.liutil.util;

import java.util.HashMap;
import java.util.Map;

public class LiClassUtil {
    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP = new HashMap<>();

    static {
        PRIMITIVE_WRAPPER_MAP.put(Boolean.TYPE, Boolean.class);
        PRIMITIVE_WRAPPER_MAP.put(Byte.TYPE, Byte.class);
        PRIMITIVE_WRAPPER_MAP.put(Character.TYPE, Character.class);
        PRIMITIVE_WRAPPER_MAP.put(Short.TYPE, Short.class);
        PRIMITIVE_WRAPPER_MAP.put(Integer.TYPE, Integer.class);
        PRIMITIVE_WRAPPER_MAP.put(Long.TYPE, Long.class);
        PRIMITIVE_WRAPPER_MAP.put(Double.TYPE, Double.class);
        PRIMITIVE_WRAPPER_MAP.put(Float.TYPE, Float.class);
        PRIMITIVE_WRAPPER_MAP.put(Void.TYPE, Void.class);
    }

    public static Class<?> primitiveToWrapper(final Class<?> cls) {
        Class<?> convertedClass = cls;
        if (cls != null && cls.isPrimitive()) {
            convertedClass = PRIMITIVE_WRAPPER_MAP.get(cls);
        }
        return convertedClass;
    }

    public static boolean isAssignableFromOrIsWrapper(Class<?> father, Class<?> son) {

        if (father != null && son != null) {

            if (father.isAssignableFrom(son)) {
                return true;
            }
            return primitiveToWrapper(son) == primitiveToWrapper(father);
        }
        return false;
    }
}
