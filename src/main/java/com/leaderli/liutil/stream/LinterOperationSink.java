package com.leaderli.liutil.stream;

import java.util.function.Predicate;

@Deprecated
public interface LinterOperationSink<T> {
    LinterPredicateSink<T> test(Predicate<T> predicate);
}
