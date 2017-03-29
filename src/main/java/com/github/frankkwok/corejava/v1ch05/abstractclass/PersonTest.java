package com.github.frankkwok.corejava.v1ch05.abstractclass;

/**
 * This program demonstrates abstract class
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class PersonTest {
    public static void main(String[] args) {
        Person[] people = new Person[2];

        people[0] = new Student("Frank Kwok", "computer science");
        people[1] = new Employee("Carl Cracker", 80000, 1987, 12, 15);

        for (Person person : people) {
            System.out.println(person.getDescription());
        }
    }
}
