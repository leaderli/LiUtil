package com.leaderli.liutil.util;

import java.util.*;
import java.util.stream.Collectors;

public class LiListUtil {

    public static <T> List<T> getDuplicateElement(List<T> list) {

        if (list == null || list.size() == 0) {
            return Collections.emptyList();
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
}
