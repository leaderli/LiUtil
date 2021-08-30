package com.leaderli.liutil.util;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

public class LiClassUtil {


    public static Class<?> unWrapper(Class<?> origin) {

        if (ClassUtils.isPrimitiveWrapper(origin)) {
            return ClassUtils.wrapperToPrimitive(origin);
        }
        return origin;
    }

    public static Class<?> getDirectSpecificSingleGenericInterfacesActualType(Class<?> cls, Class<?> inter) {

        Optional<ParameterizedType> any = Arrays.stream(cls.getGenericInterfaces())
                .filter(generic -> generic instanceof ParameterizedType)
                .map(generic -> (ParameterizedType) generic)
                .filter(type -> type.getActualTypeArguments().length == 1)
                .filter(type -> type.getRawType() == inter
                ).findAny();

        if (any.isPresent()) {

            Type actualTypeArgument = any.get().getActualTypeArguments()[0];
            if (actualTypeArgument instanceof Class) {
                return (Class<?>) actualTypeArgument;
            }
        }


        return Object.class;
    }

    private static Class<?> getSingleGenericActualType(Type generic) {
        if (generic instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) generic).getActualTypeArguments();
            if (actualTypeArguments.length == 1 && actualTypeArguments[0] instanceof Class) {
                return (Class<?>) actualTypeArguments[0];
            }
        }
        return null;
    }

    public static Class<?> getDirectSingleGenericSupperClassActualType(Class<?> cls) {

        Type genericSuperclass = cls.getGenericSuperclass();
        Class<?> actualTypeArguments = getSingleGenericActualType(genericSuperclass);

        if (actualTypeArguments != null) {
            return actualTypeArguments;
        }
        return Object.class;
    }

}
