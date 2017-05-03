package com.github.frankkwok.corejava.v2ch06.timeline;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Frank Kwok on 2017/5/3.
 */
public class Timeline {
    private static final int SIZE = 11;

    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgorithm(SIZE);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        long millis = duration.toMillis();
        System.out.printf("%d milliseconds\n", millis);

        Instant start2 = Instant.now();
        runAlgorithm2(SIZE);
        Instant end2 = Instant.now();
        Duration duration2 = Duration.between(start2, end2);
        millis = duration2.toMillis();
        System.out.printf("%d milliseconds\n", millis);

        boolean overTenTimesFaster = duration.multipliedBy(10)
                .minus(duration2).isNegative();
        System.out.printf("The first algorithm is %smore than ten times faster", overTenTimesFaster ? "" : "not ");
    }

    private static void runAlgorithm(int size) {
        List<Integer> list = new Random()
                .ints()
                .map(i -> i % 100)
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
        Collections.sort(list);
        System.out.println(list);
    }

    private static void runAlgorithm2(int size) {
        List<Integer> list = new Random()
                .ints()
                .map(i -> i % 100)
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
        while (!IntStream.range(1, list.size())
                .allMatch(i -> list.get(i - 1).compareTo(list.get(i)) <= 0)) {
            Collections.shuffle(list);
        }
        System.out.println(list);
    }
}
