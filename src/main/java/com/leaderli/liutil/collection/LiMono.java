package com.leaderli.liutil.collection;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public Optional<T> get() {
        return Optional.ofNullable(element);
    }

    public boolean isPresent() {
        return element != null;
    }

    public T get(T def) {
        if(isPresent()){
            return element;
        }else {
            return def;
        }
    }
    public LiMono<T> or(T or) {
        if (isPresent()) {
            return this;
        }
        return LiMono.of(or);
    }
}
