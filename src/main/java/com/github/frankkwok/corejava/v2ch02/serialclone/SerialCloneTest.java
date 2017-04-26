package com.github.frankkwok.corejava.v2ch02.serialclone;

/**
 * @author Frank Kwok on 2017/4/26.
 */
public class SerialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Employee harry2 = (Employee) harry.clone();

        harry.raiseSalary(5);

        System.out.println(harry);
        System.out.println(harry2);
    }
}
