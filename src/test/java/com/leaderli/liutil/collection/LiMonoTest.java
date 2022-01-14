package com.leaderli.liutil.collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked", "OptionalGetWithoutIsPresent"})
public class LiMonoTest {

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
                .error(() -> System.out.println("hhhhhhh"))
                .to(Body::getRequest)
                .to(Request::getName)
                .then(name -> System.out.println("the name is :" + name))
                .error(() -> System.out.println("there is something error when get name"));

        Body body = new Body();
        Request request = new Request();
        request.setName("hello");
        body.setRequest(request);
        data.setBody(body);

        LiMono.of(data)
                .to(Data::getBody)
                .error(() -> System.out.println("hhhhhhh"))
                .to(Body::getRequest)
                .to(Request::getName)
                .then(name -> System.out.println("the name is :" + name))
                .error(() -> System.out.println("there is something error when get name"));

    }

    @Test
    public void test3() {
        Data data = new Data();

        Optional<String> name = LiMono.of(data)
                .to(Data::getBody).error(() -> System.out.println("error"))
                .to(Body::getRequest)
                .to(Request::getName)
                .or("123").getOr();

        assert !name.isPresent() || "123".equals(name.get());

    }

    @Test
    public void test4() {

        Object obj = new HashMap<>();

        LiMono.of(obj)
                .cast(Map.class)
                .then(map -> {
                    System.out.println(map.size());
                    System.out.println(map);
                })
                .cast(List.class)
                .then(list -> {

                    System.out.println(list.size());
                    System.out.println(list);
                });

    }

    @Test
    public void test5() {

        List list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add(1);

        List<String> arr = LiMono.of(list).castList(String.class).getOr().get();

        assert arr.size() == 2;

        Map map = new HashMap<>();

        map.put("1", "1");
        map.put("2", 2);
        map.put(3, 3);


        Map<String, String> stringMap = LiMono.of(map).castMap(String.class, String.class).getOr().get();

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
    }
}
