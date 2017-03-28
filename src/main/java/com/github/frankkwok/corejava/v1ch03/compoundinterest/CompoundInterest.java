package com.github.frankkwok.corejava.v1ch03.compoundinterest;

/**
 * This program demonstrates how to store data in a 2D array.
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class CompoundInterest {
    public static void main(String[] args) {
        final int RATES_NUMBER = 6;
        final int YEARS_NUMBER = 10;

        final double START_RATE = 10;

        double[] interestRates = new double[RATES_NUMBER];
        for (int i = 0; i < interestRates.length; i++) {
            interestRates[i] = (START_RATE + i) / 100;
        }

        double[][] balances = new double[YEARS_NUMBER][RATES_NUMBER];

        for (int i = 0; i < balances.length; i++) {
            for (int j = 0; j < balances[i].length; j++) {
                if (i == 0) {
                    balances[i][j] = 10000;
                } else {
                    balances[i][j] = balances[i - 1][j] * (1 + interestRates[j]);
                }
            }
        }

        for (double d : interestRates) {
            System.out.printf("%10.0f%%", d * 100);
        }
        System.out.println();

        for (double[] row : balances) {
            for (double d : row) {
                System.out.printf("%10.2f ", d);
            }
            System.out.println();
        }
    }
}
