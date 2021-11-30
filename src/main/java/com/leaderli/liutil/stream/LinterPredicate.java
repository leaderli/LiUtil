package com.leaderli.liutil.stream;

public interface LinterPredicate<T> {
    LinterCombineOperation<T> and();
    LinterCombineOperation or();

}
