package com.leaderli.liutil.util;

import java.util.Map;

public class LiMapUtil {

    public static Map override(Map origin, Map override) {

        return _override(origin, override);
    }

    @SuppressWarnings("unchecked")
    private static <T> T _override(T origin, T override) {

        if (origin == null) {
            return override;
        }

        if (override == null) {
            return origin;
        }

        if (origin instanceof Map && override instanceof Map) {

            Map _return = (Map) origin;
            _return.forEach((k, v) -> _return.put(k, _override(v, ((Map) override).get(k))));
            return (T) _return;
        }

        return override;

    }
}
