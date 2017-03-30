package com.github.frankkwok.corejava.v1ch08.pair2;

import java.time.LocalDate;

/**
 * @author Frank Kwok on 2017/3/30.
 */
public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] dates = new LocalDate[]{
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1815, 12, 10),
                LocalDate.of(1903, 12, 3),
                LocalDate.of(1910, 6, 22)
        };
        Pair<LocalDate> minMax = ArrayAlg.minMax(dates);
        System.out.println("min = " + minMax.getFirst());
        System.out.println("max = " + minMax.getSecond());
    }
}

class ArrayAlg {
    @SuppressWarnings("unchecked")
    static <T extends Comparable> Pair<T> minMax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (T t : a) {
            if (t.compareTo(min) < 0) {
                min = t;
            }
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return new Pair<>(min, max);
    }
}
