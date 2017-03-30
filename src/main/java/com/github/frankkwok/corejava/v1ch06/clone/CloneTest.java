package com.github.frankkwok.corejava.v1ch06.clone;

/**
 * This program demonstrates cloning
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class CloneTest {
    public static void main(String[] args) {
        try {
            Employee original = new Employee("Carl Cracker", 75000, 1987, 12, 15);
            Employee copy = original.clone();
            copy.setHireDay(1989, 10, 1);
            System.out.println("original=" + original);
            System.out.println("copy=" + copy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
