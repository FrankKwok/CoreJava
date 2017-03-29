package com.github.frankkwok.corejava.v1ch05.enumtest;

import java.util.Scanner;

/**
 * This program demonstrates enum type
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class EnumTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
        String input = scanner.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size=" + size + ", abbr=" + size.getAbbr());
    }
}
