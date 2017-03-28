package com.github.frankkwok.corejava.v1ch03.inputtest;

import java.util.Scanner;

/**
 * This program demonstrates console input
 *
 * @author Frank Kwok on 2017/3/28.
 */
public class InputTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get name
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        // get age
        System.out.println("How old are you?");
        int age = scanner.nextInt();

        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
    }
}
