package com.leaderli.liutil.collection;

import com.leaderli.liutil.exception.LiMonoRuntimeException;
import com.leaderli.liutil.util.LiCastUtil;
import com.leaderli.liutil.util.LiClassUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A container object which contain a value
 *
 * @param <T> the type parameter of value
 */
public class LiMono<T> {

    private final T value;


    private LiMono(T value) {
        this.value = value;
    }


    public static <T> LiMono<T> of(T t) {

        return new LiMono<>(t);
    }


    public static <T> LiMono<T> empty() {

        return new LiMono<>(null);
    }

    /**
     * @param mapping a function to apply value
     * @param <R>     the type parameter of mapped value
     * @return the new LiMono with type <R>
     */
    public <R> LiMono<R> map(Function<T, R> mapping) {
        if (value != null && mapping != null) {
            return LiMono.of(mapping.apply(this.value));
        }
        return LiMono.empty();
    }

    /**
     * @param consumer apply  value when  value {@link #isPresent()}
     * @return this
     */
    public LiMono<T> then(Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
        return this;
    }

    /**
     * @param runnable run when value {@link #notPresent()}
     * @return this
     */
    public LiMono<T> error(Runnable runnable) {
        if (value == null) {
            runnable.run();
        }
        return this;
    }

    /**
     * @param supplier a {@link Supplier} whose result is returned if no value is present
     * @return this if value present otherwise the new LiMono with result of {@link Supplier#get()}
     */
    public LiMono<T> error(Supplier<T> supplier) {
        if (value == null) {
            return LiMono.of(supplier.get());
        }
        return this;
    }

    /**
     * @return this
     * @throws RuntimeException - when value is null
     */
    public LiMono<T> exception() {
        if (value == null) {
            throw new LiMonoRuntimeException("value is null ");
        }
        return this;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean notPresent() {
        return value == null;
    }


    public T get() {
        return value;
    }


    /**
     * @param def the value is returned if no value is present
     * @return value if present otherwise the def
     */
    public T getOrOther(T def) {
        return or(def).get();
    }

    /**
     * @param other the value is returned if no value is present
     * @return this if value is present otherwise a new LiMono with value of other
     */
    public LiMono<T> or(T other) {
        if (isPresent()) {
            return this;
        }
        return LiMono.of(other);
    }

    /**
     * @param other the value is returned if no value is present
     * @return this if value is present otherwise return other
     */
    public LiMono<T> or(LiMono<T> other) {
        if (isPresent()) {
            return this;
        }
        return other;
    }

    /**
     * @param predicate the function predict value should remain
     * @return this if value matches the predicate otherwise {@link #empty()}
     */
    public LiMono<T> filter(Predicate<T> predicate) {


        if (Boolean.TRUE.equals(map(predicate::test).getOrOther(false))) {
            return this;
        }

        return LiMono.empty();

    }


    /**
     * @param type the type of the list item value can be cast
     * @param <R>  the type parameter of the list item
     * @return return {@link LiFlux#empty()} if value type is not  {@link List}
     * otherwise return new LiFlux with type value
     */
    public <R> LiFlux<R> flux(Class<R> type) {

        @SuppressWarnings("rawtypes")
        LiMono<List> listMono = cast(List.class);
        return LiFlux.of(LiCastUtil.cast(listMono.getOrOther(null), type));
    }

    /**
     * @param type the type of value can be cast
     * @param <R>  the type parameter to the value
     * @return new LiMono with value of casted type
     */
    public <R> LiMono<R> cast(Class<R> type) {
        //noinspection unchecked
        return (LiMono<R>) filter(monoElement -> LiClassUtil.isAssignableFromOrIsWrapper(type, monoElement.getClass()));
    }

    /**
     * @param keyType   the type of map key
     * @param valueType the type of map value
     * @param <K>       the type parameter of map key
     * @param <V>       the type parameter of map value
     * @return return {@link #empty()} if value is not {@link Map}
     * otherwise return new LiMono with Map<K,V>
     */
    public <K, V> LiMono<Map<K, V>> cast(Class<K> keyType, Class<V> valueType) {

        @SuppressWarnings("rawtypes")
        LiMono<Map> listMono = cast(Map.class);
        return LiMono.of(LiCastUtil.cast(listMono.getOrOther(null), keyType, valueType));
    }
}
