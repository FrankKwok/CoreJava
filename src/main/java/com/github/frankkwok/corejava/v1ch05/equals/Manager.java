package com.github.frankkwok.corejava.v1ch05.equals;

/**
 * @author Frank Kwok on 2017/3/29.
 */
class Manager extends Employee {
    private double bonus;

    Manager(String name, double salary, int year, int month, int dayOfMonth) {
        super(name, salary, year, month, dayOfMonth);
        bonus = 0;
    }

    void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    double getSalary() {
        return super.getSalary() + bonus;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) {
            return false;
        }
        Manager other = (Manager) otherObject;
        return bonus == other.bonus;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Double.hashCode(bonus);
    }

    @Override
    public String toString() {
        return super.toString() + "[bonus=" + bonus + "]";
    }
}
