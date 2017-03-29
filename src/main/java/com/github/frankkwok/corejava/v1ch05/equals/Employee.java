package com.github.frankkwok.corejava.v1ch05.equals;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Frank Kwok on 2017/3/29.
 */
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

    @Override
    public boolean equals(Object otherObject) {
        // this == otherObject;
        if (super.equals(otherObject)) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Employee other = (Employee) otherObject;
        return Objects.equals(name, other.name) &&
                salary == other.salary &&
                Objects.equals(hireDay, other.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ", salary=" + salary + ", hireDay" + hireDay + "]";
    }
}
