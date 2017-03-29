package com.github.frankkwok.corejava.v1ch04.paramtest;

/**
 * This program demonstrates parameter passing in Java
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class ParamTest {
    public static void main(String[] args) {
        System.out.println("Test tripleValue:");
        double value = 10;
        System.out.println("Before: value=" + value);
        tripleValue(value);
        System.out.println("After: value=" + value);

        System.out.println("Test tripleSalary:");
        Employee carl = new Employee("Carl Cracker", 75000);
        System.out.println("Before: salary=" + carl.getSalary());
        tripleSalary(carl);
        System.out.println("After: salary=" + carl.getSalary());

        System.out.println("Test swap:");
        Employee harry = new Employee("Harry Hacker", 50000);
        Employee tony = new Employee("Tony Tester", 40000);
        System.out.println("Before: harry=" + harry.getName());
        System.out.println("Before: tony=" + tony.getName());
        swap(harry, tony);
        System.out.println("After: harry=" + harry.getName());
        System.out.println("After: tony=" + tony.getName());
    }

    private static void tripleValue(double d) {
        d = 3 * d;
        System.out.println("End of tripleValue: d=" + d);
    }

    private static void tripleSalary(Employee employee) {
        employee.raiseSalary(200);
        System.out.println("End of tripleSalary: salary=" + employee.getSalary());
    }

    private static void swap(Employee e1, Employee e2) {
        Employee tmp = e1;
        e1 = e2;
        e2 = tmp;
        System.out.println("End of swap: e1=" + e1.getName());
        System.out.println("End of swap: e2=" + e2.getName());
    }
}

class Employee {
    private String name;
    private double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    double getSalary() {
        return salary;
    }

    void raiseSalary(double byPercent) {
        salary = salary * (1 + byPercent);
    }
}
