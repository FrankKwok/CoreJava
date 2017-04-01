package com.github.frankkwok.corejava.v1ch09.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Frank Kwok on 2017/4/1.
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }

        Iterator<String> iterator = words.iterator();
        for (int i = 0; i < 20 && iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }
        System.out.println("...");
        System.out.printf("%d distinct word. %d milliseconds.", words.size(), totalTime);
    }
}
