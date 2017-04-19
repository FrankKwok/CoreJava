package com.github.frankkwok.corejava.v2ch01.collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Frank Kwok on 2017/4/17.
 */
public class CollectingResults {
    public static void main(String[] args) throws IOException {
        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Object[] numbers = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .toArray();
        System.out.println("Object array: " + Arrays.toString(numbers));

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number: " + number);
            System.out.println("The following statement throws an exception: ");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .toArray(Integer[]::new);
        System.out.println("Integer array: " + Arrays.toString(numbers3));


        Predicate<String> filter = s -> s.length() > 0;

        Set<String> noVowelSet = noVowels().filter(filter)
                .collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().filter(filter)
                .collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels().filter(filter)
                .limit(10)
                .collect(Collectors.joining());
        System.out.println("Joining: " + result);

        result = noVowels().filter(filter)
                .limit(10)
                .collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result);

        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);
        System.out.println("forEach:");
        noVowels().limit(10)
                .forEach(System.out::println);
    }

    private static Stream<String> noVowels() throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("C:\\Users\\Frank\\Codes\\Source\\corejava\\gutenberg\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    private static <T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" + set.stream()
                .limit(10)
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + "]");
    }
}
