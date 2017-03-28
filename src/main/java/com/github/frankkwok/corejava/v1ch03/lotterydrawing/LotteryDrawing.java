package com.github.frankkwok.corejava.v1ch03.lotterydrawing;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This program demonstrates array sort.
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class LotteryDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many numbers do you need to draw?");
        int numbers = scanner.nextInt();

        System.out.println("What is the highest number you can draw?");
        int highestNumber = scanner.nextInt();

        int[] allNumbers = new int[highestNumber];
        for (int i = 0; i < allNumbers.length; i++) {
            allNumbers[i] = i + 1;
        }

        int[] result = new int[numbers];

        for (int i = 0; i < numbers; i++) {
            int randomIndex = (int) (Math.random() * highestNumber);
            result[i] = allNumbers[randomIndex];
            allNumbers[randomIndex] = allNumbers[--highestNumber];
        }

        Arrays.sort(result);
        System.out.println("Bet the following combination. It'll make you rich!");
        for (int i : result) {
            System.out.println(i);
        }
    }
}
