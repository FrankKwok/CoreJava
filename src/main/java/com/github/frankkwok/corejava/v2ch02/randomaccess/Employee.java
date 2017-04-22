package com.github.frankkwok.corejava.v2ch02.randomaccess;

import java.time.LocalDate;

/**
 * @author Frank Kwok on 2017/4/20.
 */
class Employee {
    static final int NAME_SIZE = 40;
    static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;
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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
