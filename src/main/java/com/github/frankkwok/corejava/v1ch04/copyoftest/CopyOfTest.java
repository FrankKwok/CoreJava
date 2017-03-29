package com.github.frankkwok.corejava.v1ch04.copyoftest;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * use reflection to copy array
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = new String[]{"Tom", "Dick", "Harry"};
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));

        System.out.println("The following call will generate an exception");
        b = (String[]) badCopyOf(b, 10);
    }

    private static Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
        return newArray;
    }

    private static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if (!cl.isArray()) {
            return null;
        }

        int length = Array.getLength(a);
        Class componentType = cl.getComponentType();

        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
