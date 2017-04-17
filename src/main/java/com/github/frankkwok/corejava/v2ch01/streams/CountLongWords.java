package com.github.frankkwok.corejava.v2ch01.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author Frank Kwok on 2017/4/17.
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("C:\\Users\\Frank\\Codes\\Source\\corejava\\gutenberg\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for (String s : words) {
            if (s.length() > 12) {
                count++;
            }
        }
        System.out.println(count);

        count = words.stream()
                .filter(s -> s.length() > 12)
                .count();
        System.out.println(count);

        count = words.parallelStream()
                .filter(s -> s.length() > 12)
                .count();
        System.out.println(count);
    }
}
