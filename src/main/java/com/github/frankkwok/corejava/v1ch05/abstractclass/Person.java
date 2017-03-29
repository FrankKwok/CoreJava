package com.github.frankkwok.corejava.v1ch05.abstractclass;

/**
 * @author Frank Kwok on 2017/3/29.
 */
abstract class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract String getDescription();
}
