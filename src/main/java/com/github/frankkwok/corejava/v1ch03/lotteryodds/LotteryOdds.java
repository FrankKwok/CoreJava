package com.github.frankkwok.corejava.v1ch03.lotteryodds;

import java.util.Scanner;

/**
 * This program demonstrates a <code>for</code> loop.
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class LotteryOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many numbers do you need to draw?");
        int numbers = scanner.nextInt();

        System.out.println("What is the highest number you can draw?");
        int highestNumber = scanner.nextInt();

        int lotteryOdds = 1;
        for (int i = 1; i <= numbers; i++) {
            lotteryOdds = lotteryOdds * (highestNumber - i + 1) / i;
        }

        System.out.printf("Your odds are 1 in %d. Good luck!%n", lotteryOdds);
    }
}
