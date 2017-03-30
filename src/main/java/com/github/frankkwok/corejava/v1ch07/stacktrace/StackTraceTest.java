package com.github.frankkwok.corejava.v1ch07.stacktrace;

import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/3/30.
 */
public class StackTraceTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a positive number:");
        int n = scanner.nextInt();
        factorial(n);
    }

    private static int factorial(int n) {
        System.out.println("factorial(" + n + ")");
        Throwable throwable = new Throwable();
        StackTraceElement[] elements = throwable.getStackTrace();
        for (StackTraceElement e : elements) {
            System.out.println(e);
        }
        int result;
        if (n <= 1) {
            result = 1;
        } else {
            result = n * factorial(n - 1);
        }
        System.out.println("return " + result);
        return result;
    }
}
