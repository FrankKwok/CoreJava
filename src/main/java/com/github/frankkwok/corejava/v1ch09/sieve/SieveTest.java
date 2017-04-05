package com.github.frankkwok.corejava.v1ch09.sieve;

import java.util.BitSet;

/**
 * @author Frank Kwok on 2017/4/5.
 */
public class SieveTest {
    public static void main(String[] args) {
        int number = 2_000_000_000;
        long start = System.currentTimeMillis();

        BitSet bitSet = new BitSet(number + 1);
        for (int i = 2; i <= number; i++) {
            bitSet.set(i);
        }

        int i = 2;
        int count = 0;
        while (i * i <= number) {
            if (bitSet.get(i)) {
                count++;
                int k = i * 2;
                while (k <= number) {
                    bitSet.clear(k);
                    k += i;
                }
            }
            i++;
        }
        for (int j = i; j <= number; j++) {
            if (bitSet.get(j)) {
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println((end - start) + " milliseconds");
    }
}
