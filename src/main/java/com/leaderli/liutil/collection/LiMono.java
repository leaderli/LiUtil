package com.leaderli.liutil.collection;

import com.leaderli.liutil.util.LiCastUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LiMono<T> {

    private final T element;


    public LiMono(T element) {
        this.element = element;
    }


    public static <T> LiMono<T> of(T t) {

        return new LiMono<>(t);
    }

    public static <T> LiMono<T> empty() {

        return new LiMono<>(null);
    }

    public <R> LiMono<R> to(Function<T, R> to) {
        if (element != null) {
            return LiMono.of(to.apply(this.element));
        }
        return LiMono.empty();
    }

    public LiMono<T> then(Consumer<T> consumer) {
        if (element != null) {
            consumer.accept(element);
        }
        return this;
    }

    public LiMono<T> error(Runnable runnable) {
        if (element == null) {
            runnable.run();
        }
        return this;
    }

    public boolean isPresent() {
        return element != null;
    }

    public Optional<T> get() {
        return Optional.ofNullable(element);
    }


    public T getOr(T def) {
        if (isPresent()) {
            return element;
        } else {
            return def;
        }
    }

    public LiMono<T> or(T or) {
        if (isPresent()) {
            return this;
        }
        return LiMono.of(or);
    }

    public <R> LiMono<R> cast(Class<R> type) {

        if (isPresent() && type != null && type.isAssignableFrom(this.element.getClass())) {

            //noinspection unchecked
            return (LiMono<R>) this;
        }
        return LiMono.empty();
    }

    public <R> List<LiMono<R>> stream(Class<R> type) {

        LiMono<List<R>> listLiMono = castList(type);

        return listLiMono.getOr(Collections.emptyList())
                .stream()
                .map(LiMono::of)
                .collect(Collectors.toList());

    }

    public <K, V> List<LiMono<Map<K, V>>> mapStream(Class<K> keyType, Class<V> valueType) {

        return stream(Map.class).stream()
                .map(item -> item.castMap(keyType, valueType))
                .filter(LiMono::isPresent)
                .collect(Collectors.toList());
    }


    public <R> LiMono<List<R>> castList(Class<R> type) {

        @SuppressWarnings("rawtypes")
        LiMono<List> listMono = cast(List.class);
        return LiMono.of(LiCastUtil.cast(listMono.getOr(null), type));
    }

    public <K, V> LiMono<List<Map<K, V>>> castListMap(Class<K> keyType, Class<V> valueType) {

        @SuppressWarnings("rawtypes")
        LiMono<List<Map>> listMapMono = castList(Map.class);

        return listMapMono.to(list -> list.stream()
                .map(item -> LiCastUtil.cast(item, keyType, valueType))
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toList()));

    }

    public <K, V> LiMono<Map<K, V>> castMap(Class<K> keyType, Class<V> valueType) {

        @SuppressWarnings("rawtypes")
        LiMono<Map> listMono = cast(Map.class);
        return LiMono.of(LiCastUtil.cast(listMono.getOr(null), keyType, valueType));
    }
}
