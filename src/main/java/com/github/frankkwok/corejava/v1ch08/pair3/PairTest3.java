package com.github.frankkwok.corejava.v1ch08.pair3;

/**
 * @author Frank Kwok on 2017/3/30.
 */
public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);

        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);
        Manager[] managers = new Manager[]{ceo, cfo};

        Pair<Employee> result = new Pair<>();
        minMaxBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());
        maxMinBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());
    }

    private static void printBuddies(Pair<? extends Employee> pair) {
        System.out.println(pair.getFirst().getName() + " and " + pair.getSecond().getName() + " are buddies.");
    }

    private static void minMaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a == null || a.length == 0) {
            return;
        }
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].getBonus() < min.getBonus()) {
                min = a[i];
            }
            if (a[i].getBonus() > max.getBonus()) {
                max = a[i];
            }
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    private static void maxMinBonus(Manager[] a, Pair<? super Manager> result) {
        minMaxBonus(a, result);
        PairAlg.swapHelper(result);
    }
}

class PairAlg {

    static boolean hasNulls(Pair<?> pair) {
        return pair.getFirst() == null || pair.getSecond() == null;
    }

    static void swap(Pair<?> pair) {
        swapHelper(pair);
    }

    static <T> void swapHelper(Pair<T> pair) {
        T first = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(first);
    }
}
