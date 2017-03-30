package com.github.frankkwok.corejava.v1ch06.lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * lambda expressions
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[]{
                "Mercury", "Venus", "Earth",
                "Mars", "Jupiter", "Saturn",
                "Uranus", "Neptune"
        };

        System.out.println(Arrays.toString(planets));
        System.out.println("Sort in dictionary order:");
        Arrays.sort(planets);
        System.out.println("Sort by length: ");
        System.out.println(Arrays.toString(planets));
        Arrays.sort(planets, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(planets));


        Timer timer = new Timer(3000, event -> System.out.println("The time is " + new Date()));
        timer.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
