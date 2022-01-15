package com.leaderli.liutil.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class LiFluxTest {


    @Test
    public void of() {

        Assert.assertTrue(LiFlux.empty().isEmpty());
        Assert.assertTrue(LiFlux.of(1).notEmpty());


        Assert.assertTrue(LiFlux.of(new ArrayList<>()).isEmpty());
        assertEquals(2, LiFlux.of(Arrays.asList(1, 2)).size());
    }

    @Test
    public void get() {

        LiFlux<Integer> flux = LiFlux.of(1, 2, 3);
        assertEquals(3, flux.size());

        List<LiMono<Integer>> liMonos = flux.get();
        liMonos.add(LiMono.of(4));

        assertEquals(4, liMonos.size());
        assertEquals(3, flux.size());

    }

    @Test
    public void getOr() {
        List<LiMono<Integer>> flux = LiFlux.<Integer>empty().getOr(1, 2, 3);
        assertEquals(3, flux.size());
        flux = LiFlux.of(1).getOr(1, 2, 3);
        assertEquals(1, flux.size());
    }

    @Test
    public void or() {
        LiFlux<Integer> flux = LiFlux.<Integer>empty().or(1, 2, 3);
        assertEquals(3, flux.size());

        flux = LiFlux.of(1).or(1, 2, 3);
        assertEquals(1, flux.size());

    }

    @Test
    public void cast() {

        List<Object> objs = Arrays.asList(1, 2, 3);
        LiFlux<Object> of = LiFlux.of(objs);

        LiFlux<Integer> cast = of.cast(Integer.class);

        assertEquals(3, cast.size());

    }

    @Test
    public void mapCast() {
        ArrayList<Object> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("m1", "1");
        map1.put("m2", 2);

        list.add(map1);
        list.add("1");
        list.add("2");
        list.add(1);

        LiFlux<Map<String, String>> mono = LiMono.of(list).flux(Map.class).cast(String.class, String.class);

        assertEquals(1, mono.size());


    }

    @Test
    public void trim() {
        LiFlux<Integer> flux = LiFlux.of(1, null, 2);

        assertEquals(3, flux.size());
        flux.trim();
        assertEquals(3, flux.size());

        flux = flux.trim();
        assertEquals(2, flux.size());

    }

    @Test
    public void filter() {
        LiFlux<Integer> flux = LiFlux.of(1, null, 2);

        assertEquals(3, flux.size());
        flux.filter(null);
        assertEquals(3, flux.size());

        flux = flux.filter(null);
        assertEquals(2, flux.size());

        flux = flux.filter(it->it>1);
        assertEquals(1, flux.size());

    }
}
