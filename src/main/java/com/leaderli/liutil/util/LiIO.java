package com.leaderli.liutil.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LiIO {

    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

    public static InputStream createContentStream(String content) throws IOException {
        return (new ByteArrayInputStream(content.getBytes(LiIO.DEFAULT_CHARACTER_ENCODING)));
    }
}
