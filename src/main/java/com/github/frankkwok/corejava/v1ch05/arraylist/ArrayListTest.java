package com.github.frankkwok.corejava.v1ch05.arraylist;

import java.util.ArrayList;

/**
 * This program demonstrates the ArrayList class
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
        staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
        staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));

        for (Employee e : staff) {
            System.out.println(e);
        }
    }
}
