package com.github.frankkwok.corejava.v1ch09.map;

/**
 * @author Frank Kwok on 2017/4/5.
 */
public class Employee {
    private String name;
    private double salary;

    public Employee(String name) {
        this.name = name;
        salary = 0;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", salary=" + salary + "}";
    }
}
