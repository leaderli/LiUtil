package com.leaderli.liutil.util;

import com.leaderli.liutil.event.LiEvent;
import org.junit.Test;

import java.util.function.Consumer;

public class LiClassUtilTest {

    @Test
    public void testUnWrapper() {

        assert int.class == LiClassUtil.unWrapper(Integer.class);
        assert String.class == LiClassUtil.unWrapper(String.class);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetDirectGenericSupperClassActualType() {

        LiEvent<String> event = new LiEvent<String>("123") {
        };


        Class<?> actualType = LiClassUtil.getDirectSingleGenericSupperClassActualType(event.getClass());

        assert actualType == String.class;

        // noinspection unchecked
        event = new LiEvent("123") {
        };
        actualType = LiClassUtil.getDirectSingleGenericSupperClassActualType(event.getClass());
        assert actualType == Object.class;

    }

    @Test

    public void testGetDirectGenericInterfacesActualType(){

        //noinspection Convert2Lambda
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };

        assert String.class ==  LiClassUtil.getDirectSpecificSingleGenericInterfacesActualType(consumer.getClass(), Consumer.class);

    }
}