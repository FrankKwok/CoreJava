package com.github.frankkwok.corejava.v1ch06.staticinnerclass;

/**
 * This program demonstrates the static inner class
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] doubles = new double[20];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = Math.random() * 100;
        }
        ArrayAlg.Pair pair = ArrayAlg.minMax(doubles);
        System.out.println("min = " + pair.getFirst());
        System.out.println("max = " + pair.getSecond());
    }
}

class ArrayAlg {
    static Pair minMax(double[] doubles) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double d : doubles) {
            if (d < min) {
                min = d;
            }
            if (d > max) {
                max = d;
            }
        }
        return new Pair(min, max);
    }

    static class Pair {
        private double first;
        private double second;

        Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        double getFirst() {
            return first;
        }

        double getSecond() {
            return second;
        }
    }
}
