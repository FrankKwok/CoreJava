package com.github.frankkwok.corejava.v1ch05.equals;

/**
 * This program demonstrates the equals, hashCode and toString method
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class EqualsTest {
    public static void main(String[] args) {
        Employee harry1 = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Employee harry2 = harry1;
        Employee harry3 = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Employee tony = new Employee("Tony Tester", 40000, 1990, 3, 15);

        System.out.println("harry1==harry2: " + (harry1 == harry2));
        System.out.println("harry1==harry3: " + (harry1 == harry3));
        System.out.println("harry1.equals(harry3): " + harry1.equals(harry3));
        System.out.println("harry1.equals(tony): " + harry1.equals(tony));
        System.out.println("tony.toString(): " + tony);

        Manager carl = new Manager("Carl Cracker", 75000, 1987, 12, 15);
        Manager boss = new Manager("Carl Cracker", 75000, 1987, 12, 15);
        boss.setBonus(5000);
        System.out.println("boss.toString(): " + boss);
        System.out.println("carl.equals(boss): " + carl.equals(boss));
        // print hashCode
        System.out.println("harry1.hashCode(): " + harry1.hashCode());
        System.out.println("tony.hashCode(): " + tony.hashCode());
        System.out.println("carl.hashCode(): " + carl.hashCode());
        System.out.println("boss.hashCode(): " + boss.hashCode());
    }
}
