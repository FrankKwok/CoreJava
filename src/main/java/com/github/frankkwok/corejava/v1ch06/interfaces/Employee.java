package com.github.frankkwok.corejava.v1ch06.interfaces;

/**
 * @author Frank Kwok on 2017/3/30.
 */
class Employee implements Comparable<Employee> {
    private String name;
    private double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "name=" + name + ", salary=" + salary;
    }

    @Override
    public int compareTo(Employee other) {
        return Double.compare(salary, other.salary);
    }
}
