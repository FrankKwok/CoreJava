package com.github.frankkwok.corejava.v1ch02.welcome;

/**
 * @author Frank Kwok on 2017/3/28.
 */
public class Welcome {
    public static void main(String[] args) {
        String greeting = "Welcome to Core Java!";
        printEqualitySign(greeting.length());
        System.out.println(greeting);
        printEqualitySign(greeting.length());
    }

    private static void printEqualitySign(int number) {
        for (int i = 0; i < number; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
