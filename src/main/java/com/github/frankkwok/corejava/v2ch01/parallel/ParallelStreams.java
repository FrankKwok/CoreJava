package com.github.frankkwok.corejava.v2ch01.parallel;

import com.github.frankkwok.corejava.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * @author Frank Kwok on 2017/4/20.
 */
public class ParallelStreams {
    public static void main(String[] args) throws IOException {
        String contents = new String(ResourceUtils.readAllBytes("alice30.txt"),
                StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        int[] shortWords = new int[10];
        wordList.parallelStream()
                .forEach(s -> {
                    if (s.length() < 10) {
                        shortWords[s.length()]++;
                    }
                });
        System.out.println(Arrays.toString(shortWords));

        Arrays.fill(shortWords, 0);
        wordList.parallelStream()
                .forEach(s -> {
                    if (s.length() < 10) {
                        shortWords[s.length()]++;
                    }
                });
        System.out.println(Arrays.toString(shortWords));

        Map<Integer, Long> shortWordCounts = wordList.parallelStream()
                .filter(s -> s.length() < 10)
                .collect(groupingBy(String::length, counting()));
        System.out.println(shortWordCounts);

        Map<Integer, List<String>> result = wordList.parallelStream()
                .collect(groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        result = wordList.parallelStream()
                .collect(groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream()
                .collect(groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);

        wordCounts = wordList.parallelStream()
                .collect(groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);
    }
}
