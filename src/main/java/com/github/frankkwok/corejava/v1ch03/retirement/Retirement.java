package com.github.frankkwok.corejava.v1ch03.retirement;

import java.util.Scanner;

/**
 * This program demonstrates a <code>while</code> loop.
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class Retirement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How much money do you need to retire?");
        double goal = scanner.nextDouble();

        System.out.println("How much money will you contribute every year?");
        double payment = scanner.nextDouble();

        System.out.println("Interest rate in %:");
        double interestRate = scanner.nextDouble();

        int years = 0;
        double balance = 0;

        while (balance < goal) {
            years++;
            balance = balance * (1 + interestRate / 100) + payment;
        }

        System.out.printf("You can retire in %d years.", years);
    }
}
