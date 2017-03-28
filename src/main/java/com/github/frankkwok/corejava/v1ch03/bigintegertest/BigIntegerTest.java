package com.github.frankkwok.corejava.v1ch03.bigintegertest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Use BigInteger compute the odds of winning the grand prize in a lottery
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class BigIntegerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many numbers do you need to draw?");
        int numbers = scanner.nextInt();

        System.out.println("What is the highest number you can draw?");
        int highestNumber = scanner.nextInt();

        BigInteger lotteryOdds = BigInteger.ONE;
        for (int i = 1; i <= numbers; i++) {
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(highestNumber - i + 1))
                    .divide(BigInteger.valueOf(i));
        }

        System.out.printf("Your odds are 1 in %d. Good luck!%n", lotteryOdds);
    }
}
