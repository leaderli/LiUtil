package com.leaderli.liutil.util;

import java.util.*;

@Deprecated
public class LiListUtil {

    public static <T> List<T> getDuplicateElement(List<T> list) {

        if (list == null || list.isEmpty()) {
            return LiListUtil.emptyList();
        }

        List<T> duplicate = new ArrayList<>();

        Set<T> uniq = new HashSet<>();

        for (T t : list) {
            if (!uniq.add(t)) {
                duplicate.add(t);
            }
        }

        return duplicate;

    }

    public static <T>List<T> emptyList(){
        return new ArrayList<>();
    }
}
