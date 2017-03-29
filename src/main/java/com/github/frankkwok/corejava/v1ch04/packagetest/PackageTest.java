package com.github.frankkwok.corejava.v1ch04.packagetest;

import com.github.frankkwok.corejava.v1ch04.packagetest.employee.Employee;

import static java.lang.System.out;

/**
 * This program demonstrates the use of packages.
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class PackageTest {
    public static void main(String[] args) {
        Employee carl = new Employee("Carl Cracker", 75000, 1987, 12, 15);

        carl.raiseSalary(10);

        out.println("name=" + carl.getName() + ", salary=" + carl.getSalary() + ", hireDay=" + carl.getHireDay());
    }
}
