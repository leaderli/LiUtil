package com.leaderli.liutil.stream;

import java.util.function.Predicate;

public interface LinterOperation<T> {
    LinterPredicate<T> test(Predicate<T> predicate);
}
