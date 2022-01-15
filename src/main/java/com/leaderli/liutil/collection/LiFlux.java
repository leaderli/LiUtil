package com.leaderli.liutil.collection;

import com.leaderli.liutil.util.LiCastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LiFlux<T> {


    private final List<LiMono<T>> monos;

    private LiFlux(List<LiMono<T>> monos) {
        this.monos = monos;
    }


    @SafeVarargs
    public static <T> LiFlux<T> of(T... elements) {
        List<LiMono<T>> monos = new ArrayList<>();
        for (T element : elements) {
            monos.add(LiMono.of(element));
        }
        return new LiFlux<>(monos);
    }

    public static <T> LiFlux<T> of(List<T> elements) {

        if (elements == null) {
            return LiFlux.empty();
        }
        List<LiMono<T>> monos = elements.stream()
                .map(LiMono::of)
                .collect(Collectors.toList());
        return new LiFlux<>(monos);
    }

    public static <T> LiFlux<T> empty() {
        return of();
    }

    /**
     * @param mapping convert monos value to other type
     * @param <R>     the type parameter of converted type
     * @return return new  LiFlux of type R
     */
    public <R> LiFlux<R> map(Function<T, R> mapping) {
        if (mapping != null) {
            List<LiMono<R>> new_monos = this.monos.stream().map(mono -> mono.map(mapping)).collect(Collectors.toList());
            return new LiFlux<>(new_monos);
        }
        return LiFlux.empty();
    }

    public boolean notEmpty() {
        return !monos.isEmpty();
    }

    public boolean isEmpty() {
        return monos.isEmpty();
    }

    public int size() {
        return monos.size();
    }

    /**
     * @return get a copy of the collection data, because the collection should not
     * changed by outside
     */
    public List<LiMono<T>> get() {
        return new ArrayList<>(monos);
    }

    @SafeVarargs
    public final List<LiMono<T>> getOr(T... ors) {
        return or(ors).get();
    }

    @SafeVarargs
    public final LiFlux<T> or(T... ors) {
        if (notEmpty()) {
            return this;
        }
        return LiFlux.of(ors);
    }


    /**
     * @return return new LiFlux which filter monos {@link LiMono#isPresent()}
     */
    public LiFlux<T> trim() {
        return filter(null);
    }

    /**
     * @param predicate judge collection item should remain
     * @return return new LiFlux which filter monos by predicate function
     */
    public LiFlux<T> filter(Predicate<T> predicate) {

        List<LiMono<T>> filtered = this.monos.stream()
                .filter(LiMono::isPresent)
                .filter(mono -> predicate == null || predicate.test(mono.get()))
                .collect(Collectors.toList());
        return new LiFlux<>(filtered);
    }

    /**
     * @param type the type of monos item can casted
     * @param <R>  the type parameter of  monos item
     * @return return new LiFlux of type R
     */
    public <R> LiFlux<R> cast(Class<R> type) {
        List<LiMono<R>> castMonos = this.monos.stream()
                .map(mono -> mono.cast(type))
                .collect(Collectors.toList());
        return new LiFlux<>(castMonos);
    }

    /**
     * the monos item is map
     *
     * @param keyType   the type of map key
     * @param valueType the type of map value
     * @param <K>       the type parameter of map key
     * @param <V>       the type parameter of map value
     * @return return new LiFlux of type Map<K,V>
     */
    public <K, V> LiFlux<Map<K, V>> cast(Class<K> keyType, Class<V> valueType) {
        return cast(Map.class)
                .map(map -> LiCastUtil.cast(map, keyType, valueType));
    }


}
