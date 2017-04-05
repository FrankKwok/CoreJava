package com.github.frankkwok.corejava.v1ch09.shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Frank Kwok on 2017/4/5.
 */
public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        List<Integer> winningCombination = integers.subList(0, 6);
        Collections.sort(winningCombination);
        System.out.println(winningCombination);
    }
}
