package com.github.frankkwok.corejava.v1ch06.interfaces;

import java.util.Arrays;

/**
 * implements the Comparable interface
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class EmployeeSortTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Tony Tester", 40000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Harry Hacker", 50000);

        Arrays.sort(staff);

        for (Employee e : staff) {
            System.out.println(e);
        }
    }
}
