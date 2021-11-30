package com.leaderli.liutil.stream;

import java.util.function.Function;

public interface LinterPredicate<T> extends Function<T,Boolean> {
    LinterCombineOperation<T> and();
    LinterCombineOperation<T> or();
}
