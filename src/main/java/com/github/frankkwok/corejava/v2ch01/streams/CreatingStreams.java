package com.github.frankkwok.corejava.v2ch01.streams;

import com.github.frankkwok.corejava.util.ResourceUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Frank Kwok on 2017/4/17.
 */
public class CreatingStreams {
    public static void main(String[] args) throws IOException {
        String contents = new String(ResourceUtils.readAllBytes("alice30.txt"),
                StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+")).filter(s -> s.length() > 0);
        show("words", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        Stream<String> empty = Stream.empty();
        show("empty", empty);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, bigInteger -> bigInteger.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents).filter(s -> s.length() > 0);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = ResourceUtils.lines("alice30.txt")) {
            show("lines", lines);
        }
    }

    private static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> headElements = stream.limit(SIZE + 1)
                .collect(Collectors.toList());

        System.out.print(title + ": ");
        for (int i = 0; i < headElements.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            if (i < SIZE) {
                System.out.print(headElements.get(i));
            } else {
                System.out.print("...");
            }
        }
        System.out.println();
    }
}
