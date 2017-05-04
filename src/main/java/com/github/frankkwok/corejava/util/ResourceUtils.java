package com.github.frankkwok.corejava.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * @author Frank Kwok on 2017/5/4.
 */
public class ResourceUtils {
    private static ClassLoader classLoader;

    static {
        classLoader = ResourceUtils.class.getClassLoader();
    }

    public static byte[] readAllBytes(String name) throws IOException {
        try (InputStream in = classLoader.getResourceAsStream(name)) {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            return buffer;
        }
    }

    public static Stream<String> lines(String name) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(name),
                StandardCharsets.UTF_8));
        return br.lines().onClose(asUncheckedRunnable(br));
    }

    private static Runnable asUncheckedRunnable(Closeable c) {
        return () -> {
            try {
                c.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}
