package com.github.frankkwok.corejava.v1ch04.employeetest;

import java.time.LocalDate;

/**
 * Create a new class Employee
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        for (Employee e : staff) {
            e.raiseSalary(10);
        }

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", hireDay=" + e.getHireDay());
        }
    }
}

class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    Employee(String name, double salary, int year, int month, int dayOfMonth) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, dayOfMonth);
    }

    String getName() {
        return name;
    }

    double getSalary() {
        return salary;
    }

    LocalDate getHireDay() {
        return hireDay;
    }

    void raiseSalary(double byPercent) {
        salary = salary * (1 + byPercent / 100);
    }
}
