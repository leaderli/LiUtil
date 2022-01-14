package com.leaderli.liutil.collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LiMonoTest {

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
                .then(name -> {
                    assert name.equals("hello");
                });

        Body body = new Body();
        Request request = new Request();
        request.setName("hello");
        body.setRequest(request);
        data.setBody(body);

        LiMono.of(data)
                .to(Data::getBody)
                .to(Body::getRequest)
                .to(Request::getName)
                .then(name -> {
                    assert name.equals("hello");
                })
                .error(() -> System.out.println("there is something error when get name"));

    }

    @Test
    public void test3() {
        Data data = new Data();

        Optional<String> name = LiMono.of(data)
                .to(Data::getBody).error(() -> System.out.println("error"))
                .to(Body::getRequest)
                .to(Request::getName)
                .or("123").get();

        assert !name.isPresent() || "123".equals(name.get());

    }

    @Test
    public void test4() {

        Object obj = new HashMap<>();

        LiMono.of(obj)
                .cast(Map.class)
                .then(map -> {
                    assert map.size() == 0;
                })
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
        assert arr.size() == 2;

        List<Integer> intArr = LiMono.of(list).castList(int.class).getRaw();
        List<Integer> intArr2 = LiMono.of(list).castList(Integer.class).getRaw();

        assert intArr.size() == 1;
        assert intArr2.size() == 1;

        Map map = new HashMap<>();

        map.put("1", "1");
        map.put("2", 2);
        map.put(3, 3);




        Map<String, String> stringMap = LiMono.of(map).castMap(String.class, String.class).getRaw();

        assert stringMap.size() == 1;

    }

    @Test
    public void test6() {
        List list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add(1);

        List<LiMono<String>> stream = LiMono.of(list).stream(String.class);

        assert stream.size() == 2;


        list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("m1", "1");
        map1.put("m2", 2);

        list.add(map1);
        list.add("1");
        list.add("2");
        list.add(1);
        assert LiMono.of(list).stream(Map.class).size() == 1;

        List<LiMono<Map<String, String>>> mapStream = LiMono.of(list).mapStream(String.class, String.class);
        assert mapStream.size() == 1;


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

        assert mono.isPresent();

        assert mono.getRaw().size() == 1;

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
