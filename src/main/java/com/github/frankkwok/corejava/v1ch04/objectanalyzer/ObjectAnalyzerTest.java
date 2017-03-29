package com.github.frankkwok.corejava.v1ch04.objectanalyzer;

import java.util.ArrayList;

/**
 * use reflection write a common toString method
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            squares.add(i * i);
        }
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
