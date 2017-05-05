package com.github.frankkwok.corejava.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Frank Kwok on 2017/5/4.
 */
public class ResourceUtils {
    private static ClassLoader classLoader;

    static {
        classLoader = ResourceUtils.class.getClassLoader();
    }

    public static InputStream newInputStream(String filename) {
        return classLoader.getResourceAsStream(filename);
    }

    public static byte[] readAllBytes(String filename) throws IOException {
        try (InputStream in = classLoader.getResourceAsStream(filename)) {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            return buffer;
        }
    }

    public static List<String> readAllLines(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(newInputStream(filename),
                StandardCharsets.UTF_8))) {
            List<String> result = new ArrayList<>();
            for (; ; ) {
                String line = br.readLine();
                if (line == null)
                    break;
                result.add(line);
            }
            return result;
        }
    }

    public static Stream<String> lines(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(filename),
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
