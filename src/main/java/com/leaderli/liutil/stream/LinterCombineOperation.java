package com.leaderli.liutil.stream;

import java.util.function.Function;
import java.util.function.Predicate;

public interface LinterCombineOperation<T> extends  LinterOperation<T>,LinterNotOperation<T>, Function<T,Boolean> {

}
