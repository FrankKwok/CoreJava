package com.github.frankkwok.corejava.v1ch06.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * This program demonstrates the dynamic proxy
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            elements[i] = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
        }

        Integer key = (int) (Math.random() * 1000) + 1;
        int result = Arrays.binarySearch(elements, key);
        if (result > 0) {
            System.out.println(elements[result]);
        }
    }
}

class TraceHandler implements InvocationHandler {
    private Object target;

    TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(args[i]);
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}
