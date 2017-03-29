package com.github.frankkwok.corejava.v1ch05.abstractclass;

/**
 * @author Frank Kwok on 2017/3/29.
 */
class Student extends Person {
    private String major;

    Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    String getDescription() {
        return String.format("A student named %s and major in %s.", getName(), major);
    }
}
