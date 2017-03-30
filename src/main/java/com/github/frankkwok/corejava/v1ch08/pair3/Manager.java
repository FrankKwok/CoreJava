package com.github.frankkwok.corejava.v1ch08.pair3;

/**
 * @author Frank Kwok on 2017/3/30.
 */
class Manager extends Employee {
    private double bonus;

    Manager(String name, double salary, int year, int month, int dayOfMonth) {
        super(name, salary, year, month, dayOfMonth);
        bonus = 0;
    }

    double getBonus() {
        return bonus;
    }

    void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    double getSalary() {
        return super.getSalary() + bonus;
    }
}
