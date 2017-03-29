package com.github.frankkwok.corejava.v1ch05.abstractclass;

import java.time.LocalDate;

/**
 * @author Frank Kwok on 2017/3/29.
 */
class Employee extends Person {
    private double salary;
    private LocalDate hireDay;

    Employee(String name, double salary, int year, int month, int dayOfMonth) {
        super(name);
        this.salary = salary;
        hireDay = LocalDate.of(year, month, dayOfMonth);
    }

    @Override
    String getDescription() {
        return String.format("An employee named %s with a salary %.2f and hire from %s.", getName(), salary, hireDay);
    }
}
