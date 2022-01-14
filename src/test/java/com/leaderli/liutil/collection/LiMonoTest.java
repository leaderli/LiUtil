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
                .to(Data::getBody)
                .to(Body::getRequest)
                .to(Request::getName)
                .then(name -> assertEquals("hello", name));

        Body body = new Body();
        Request request = new Request();
        request.setName("hello");
        body.setRequest(request);
        data.setBody(body);

        LiMono.of(data)
                .to(Data::getBody)
                .to(Body::getRequest)
                .to(Request::getName)
                .then(name -> assertEquals("hello", name))
                .error(() -> System.out.println("there is something error when get name"));

    }

    @Test
    public void test3() {
        Data data = new Data();

        LiMono<Body> mono = LiMono.of(data)
                .to(Data::getBody);
        assertTrue( mono.notPresent());
        Optional<String> name = mono
                .to(Body::getRequest)
                .to(Request::getName)
                .or("123").get();

        assertTrue( !name.isPresent() || "123".equals(name.get()));

    }

    @Test
    public void test4() {

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

        List<String> arr = LiMono.of(list).castList(String.class).getRaw();
        assertEquals(2, arr.size());

        List<Integer> intArr = LiMono.of(list).castList(int.class).getRaw();
        List<Integer> intArr2 = LiMono.of(list).castList(Integer.class).getRaw();

        assertEquals(1, intArr.size());
        assertEquals(1, intArr2.size());

        Map map = new HashMap<>();

        map.put("1", "1");
        map.put("2", 2);
        map.put(3, 3);


        Map<String, String> stringMap = LiMono.of(map).castMap(String.class, String.class).getRaw();

        assertEquals(1, stringMap.size());

    }

    @Test
    public void test6() {
        List list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add(1);

        List<LiMono<String>> stream = LiMono.of(list).stream(String.class);

        assertEquals(2, stream.size());


        list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("m1", "1");
        map1.put("m2", 2);

        list.add(map1);
        list.add("1");
        list.add("2");
        list.add(1);
        assertEquals(1, LiMono.of(list).stream(Map.class).size());

        List<LiMono<Map<String, String>>> mapStream = LiMono.of(list).mapStream(String.class, String.class);
        assertEquals(1, mapStream.size());


    }

    @Test
    public void test7() {
        ArrayList<Object> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("m1", "1");
        map1.put("m2", 2);

        list.add(map1);
        list.add("1");
        list.add("2");
        list.add(1);

        LiMono<List<Map<String, String>>> mono = LiMono.of(list).castListMap(String.class, String.class);

        assertTrue( mono.isPresent());

        assertEquals(1, mono.getRaw().size());

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
