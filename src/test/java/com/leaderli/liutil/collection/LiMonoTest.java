package com.leaderli.liutil.collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public void test1() throws Throwable {


        Data data = new Data();
        thrown.expect(NullPointerException.class);
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
                .or("123").get();

        assert !name.isPresent() || "123".equals(name.get());

    }

    @Test
    public void test4() throws Throwable {

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
}
