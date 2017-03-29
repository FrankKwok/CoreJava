package com.github.frankkwok.corejava.v1ch04.statictest;

/**
 * This program demonstrates static field and method
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class StaticTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000);
        staff[1] = new Employee("Harry Hacker", 50000);
        staff[2] = new Employee("Tony Tester", 40000);

        for (Employee e : staff) {
            e.setId();
        }

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", id=" + e.getId());
        }

        System.out.println("Next available id is " + Employee.getNextId());
    }
}

class Employee {
    private static int nextId = 1;

    private String name;
    private double salary;
    private int id;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public static void main(String[] args) {
        Employee employee = new Employee("Harry", 50000);
        System.out.println(employee.getName() + " " + employee.getSalary());
    }

    static int getNextId() {
        return nextId;
    }

    String getName() {
        return name;
    }

    double getSalary() {
        return salary;
    }

    int getId() {
        return id;
    }

    void setId() {
        id = nextId++;
    }
}
