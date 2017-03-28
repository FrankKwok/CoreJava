package com.github.frankkwok.corejava.v1ch03.retirement2;

import java.util.Scanner;

/**
 * This program demonstrates a <code>do-while</code> loop.
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class Retirement2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How much money will you contribute every year?");
        double payment = scanner.nextDouble();

        System.out.println("Interest rate in %:");
        double interestRate = scanner.nextDouble();

        int years = 0;
        double balance = 0;
        String input;

        do {
            years++;
            balance = balance * (1 + interestRate / 100) + payment;
            System.out.printf("After %d years, your balance is %.2f%n", years, balance);

            // ask if ready to retire
            System.out.println("Ready to retire?(Y/N)");
            input = scanner.next();
        } while (input.equals("N"));
    }
}
