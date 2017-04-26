package com.github.frankkwok.corejava.v2ch02.objectstream;

/**
 * @author Frank Kwok on 2017/4/26.
 */
class Manager extends Employee {
    private Employee secretary;

    Manager(String name, double salary, int year, int month, int dayOfMonth) {
        super(name, salary, year, month, dayOfMonth);
        secretary = null;
    }

    void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "secretary=" + secretary +
                '}';
    }
}
