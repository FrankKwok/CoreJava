package com.github.frankkwok.corejava.v1ch03.lotteryarray;

/**
 * This program demonstrates a triangular array.
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class LotteryArray {
    public static void main(String[] args) {
        final int MAX_NUMBER = 10;

        int[][] odds = new int[MAX_NUMBER + 1][];
        for (int i = 0; i < odds.length; i++) {
            odds[i] = new int[i + 1];
        }

        for (int i = 0; i < odds.length; i++) {
            for (int j = 0; j < odds[i].length; j++) {
                int lotteryOdds = 1;
                for (int k = 1; k <= j; k++) {
                    lotteryOdds = lotteryOdds * (i - k + 1) / k;
                }
                odds[i][j] = lotteryOdds;
            }
        }

        for (int[] row : odds) {
            for (int i : row) {
                System.out.printf("%5d", i);
            }
            System.out.println();
        }
    }
}
