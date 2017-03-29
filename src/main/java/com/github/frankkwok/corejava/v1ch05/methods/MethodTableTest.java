package com.github.frankkwok.corejava.v1ch05.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * invoke methods by reflection
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class MethodTableTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    public static double square(double x) {
        return x * x;
    }

    private static void printTable(double from, double to, int number, Method method) {
        System.out.println(method);

        double dx = (to - from) / (number - 1);

        for (double d = from; d <= to; d += dx) {
            try {
                double result = (double) method.invoke(null, d);
                System.out.printf("%10.4f | %10.4f%n", d, result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
