package com.github.frankkwok.corejava.v1ch08.pair1;

/**
 * @author Frank Kwok on 2017/3/30.
 */
public class PairTest1 {
    public static void main(String[] args) {
        String[] planets = new String[]{
                "Mercury", "Venus", "Earth",
                "Mars", "Jupiter", "Saturn",
                "Uranus", "Neptune"
        };
        Pair<String> minMax = ArrayAlg.minMax(planets);
        System.out.println("min = " + minMax.getFirst());
        System.out.println("max = " + minMax.getSecond());
    }
}

class ArrayAlg {
    static Pair<String> minMax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (String s : a) {
            if (s.compareTo(min) < 0) {
                min = s;
            }
            if (s.compareTo(max) > 0) {
                max = s;
            }
        }
        return new Pair<>(min, max);
    }
}
