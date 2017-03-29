package com.github.frankkwok.corejava.v1ch05.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 * Use of Reflection
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class ReflectionTest {
    public static void main(String[] args) {
        String className;

        if (args.length > 0) {
            className = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter class name (e.g. java.util.Date):");
            className = scanner.next();
        }

        try {
            Class cl = Class.forName(className);
            Class superclass = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + className);
            if (superclass != null && superclass != Object.class) {
                System.out.print(" extends " + superclass.getName() + " ");
            }
            System.out.println("{");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printConstructors(Class cl) {
        for (Constructor constructor : cl.getDeclaredConstructors()) {
            System.out.print("    ");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " ");
            }
            System.out.print(constructor.getName() + "(");

            Class[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void printMethods(Class cl) {
        for (Method method : cl.getDeclaredMethods()) {
            System.out.print("    ");
            String modifiers = Modifier.toString(method.getModifiers());
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " ");
            }
            System.out.print(method.getReturnType().getName() + " ");
            System.out.print(method.getName() + "(");

            Class[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void printFields(Class cl) {
        for (Field field : cl.getDeclaredFields()) {
            System.out.print("    ");
            String modifiers = Modifier.toString(field.getModifiers());
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " ");
            }
            System.out.println(field.getType().getName() + " " + field.getName() + ";");
        }
    }
}
