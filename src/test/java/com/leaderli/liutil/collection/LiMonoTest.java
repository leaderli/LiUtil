package com.leaderli.liutil.collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LiMonoTest extends Assert {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test1() {


        Data data = new Data();
        thrown.expect(NullPointerException.class);
        @SuppressWarnings("unused")
        String name = data.getBody().getRequest().getName();

    }

    @Test
    public void test() {


        Data data = new Data();

        LiMono.of(data)
                .map(Data::getBody)
                .map(Body::getRequest)
                .map(Request::getName)
                .then(name -> assertEquals("hello", name));

        Body body = new Body();
        Request request = new Request();
        request.setName("hello");
        body.setRequest(request);
        data.setBody(body);

        LiMono.of(data)
                .map(Data::getBody)
                .map(Body::getRequest)
                .map(Request::getName)
                .then(name -> assertEquals("hello", name))
                .error(() -> System.out.println("there is something error when get name"));

    }

    @Test
    public void test3() {
        Data data = new Data();

        LiMono<Body> mono = LiMono.of(data)
                .map(Data::getBody);
        assertTrue(mono.notPresent());
        LiMono<String> name = mono
                .map(Body::getRequest)
                .map(Request::getName)
                .or("123");

        assertTrue(!name.isPresent() || "123".equals(name.get()));

    }

    @Test
    public void cast1() {

        Object obj = new HashMap<>();

        LiMono.of(obj)
                .cast(Map.class)
                .then(map -> assertEquals(0, map.size()))
                .cast(List.class)
                .then(list -> {

                });

    }

    @Test
    public void test5() {

        List list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add(1);

        LiFlux<String> flux = LiMono.of(list).flux(String.class);
        assertEquals(2, flux.size());

        LiFlux<Integer> intArr = LiMono.of(list).flux(int.class);
        LiFlux<Integer> intArr2 = LiMono.of(list).flux(Integer.class);

        assertEquals(1, intArr.size());
        assertEquals(1, intArr2.size());

        Map map = new HashMap<>();

        map.put("1", "1");
        map.put("2", 2);
        map.put(3, 3);


        Map<String, String> stringMap = LiMono.of(map).cast(String.class, String.class).get();

        assertEquals(1, stringMap.size());

    }

    @Test
    public void test6() {
        List list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add(1);

        List<LiMono<String>> stream = LiMono.of(list).flux(String.class).getMonoCopy();

        assertEquals(2, stream.size());


        list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("m1", "1");
        map1.put("m2", 2);

        list.add(map1);
        list.add("1");
        list.add("2");
        list.add(1);
        assertEquals(1, LiMono.of(list).flux(Map.class).size());

        LiFlux<Map<String, String>> mapStream = LiMono.of(list).flux(Map.class).cast(String.class, String.class);
        assertEquals(1, mapStream.size());


    }


    @Test
    public void filter() {
        LiMono<String> mono = LiMono.of(null);

        Assert.assertNull(mono.filter(Objects::nonNull).get());
        Assert.assertNull(mono.filter(str -> str.length() == 4).get());

        mono = LiMono.of("123");
        Assert.assertNotNull(mono.filter(Objects::nonNull).get());
        Assert.assertNull(mono.filter(str -> str.length() == 4).get());

//
        mono = LiMono.of("123");
        Assert.assertNotNull(mono.filter(LiMono::of).get());

        Assert.assertNull(mono.cast(int.class).cast(String.class).get());
        Assert.assertNull(mono.filter(it -> LiMono.of(it).cast(int.class).cast(String.class)).get());

        Assert.assertNull(mono.filter(it -> null).get());
        Assert.assertNotNull(mono.filter(it -> 1).get());

        Assert.assertNull(mono.filter(it -> LiFlux.empty()).get());
        Assert.assertNotNull(mono.filter(it -> LiFlux.of(1)).get());
//        LiMono<String> filter = LiMono.of(null);
//        LiMono<Boolean> cast = filter.cast(Boolean.class);
//        System.out.println(cast);
//        LiMono<Boolean> or = cast
//                .or(filter.cast(LiMono.class).isPresent());
//        System.out.println(or);
//        LiMono<Boolean> or1 = or
//                .or(filter.cast_map(LiFlux.class, LiFlux::notEmpty));
//        System.out.println(or1);
//        System.out.println(or1.getOrOther(false));

    }

    @Test
    public void getOr() {

        String or = LiMono.<String>of(null).getOrOther("123");
        Assert.assertEquals("123", or);
    }

    private static class Data {

        private Body body;

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }
    }

    private static class Body {

        private Request request;

        public Request getRequest() {
            return request;
        }

        public void setRequest(Request request) {
            this.request = request;
        }
    }

    private static class Request {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
