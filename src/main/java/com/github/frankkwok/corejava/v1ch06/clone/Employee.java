package com.github.frankkwok.corejava.v1ch06.clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Frank Kwok on 2017/3/30.
 */
class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;

    Employee(String name, double salary, int year, int month, int dayOfMonth) {
        this.name = name;
        this.salary = salary;
        hireDay = new GregorianCalendar(year, month - 1, dayOfMonth).getTime();
    }

    void setHireDay(int year, int month, int dayOfMonth) {
        Date newHireDay = new GregorianCalendar(year, month - 1, dayOfMonth).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return "Employee[name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }
}
