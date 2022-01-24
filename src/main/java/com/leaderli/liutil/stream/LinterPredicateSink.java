package com.leaderli.liutil.stream;

import java.util.function.Function;

@Deprecated
public interface LinterPredicateSink<T> extends Function<T,Boolean> {
    LinterCombineOperationSink<T> and();
    LinterCombineOperationSink<T> or();
}
