package com.github.frankkwok.corejava.v1ch04.packagetest.employee;

import java.time.LocalDate;

/**
 * @author Frank Kwok on 2017/3/29.
 */
public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int dayOfMonth) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, dayOfMonth);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        salary = salary * (1 + byPercent / 100);
    }
}
