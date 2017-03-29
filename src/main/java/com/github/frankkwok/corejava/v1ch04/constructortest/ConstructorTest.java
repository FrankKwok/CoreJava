package com.github.frankkwok.corejava.v1ch04.constructortest;

/**
 * This program demonstrates object construction in Java
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class ConstructorTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000);
        staff[1] = new Employee(50000);
        staff[2] = new Employee();

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", id=" + e.getId());
        }
    }
}

class Employee {
    private static int nextId;

    private String name = "";
    private double salary;
    private int id;

    static {
        nextId = (int) (Math.random() * 10000);
    }

    {
        id = nextId++;
    }

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    Employee(double salary) {
        this("Employee #" + nextId, salary);
    }

    Employee() {
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
}
