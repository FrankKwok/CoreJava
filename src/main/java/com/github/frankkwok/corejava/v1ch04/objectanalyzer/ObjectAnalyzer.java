package com.github.frankkwok.corejava.v1ch04.objectanalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author Frank Kwok on 2017/3/29.
 */
class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    String toString(Object object) {
        if (object == null) {
            return "null";
        }

        if (visited.contains(object)) {
            return "...";
        }
        visited.add(object);

        Class cl = object.getClass();
        if (cl == String.class) {
            return (String) object;
        }

        StringBuilder result = new StringBuilder();

        if (cl.isArray()) {

            Class componentType = cl.getComponentType();
            int length = Array.getLength(object);

            result.append(componentType.getName());
            result.append("[]{");
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    result.append(",");
                }
                Object value = Array.get(object, i);
                if (componentType.isPrimitive()) {
                    result.append(value);
                } else {
                    result.append(toString(value));
                }
            }
            result.append("}");
        } else {
            result.append(cl.getName());
            do {
                result.append("[");
                Field[] fields = cl.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);

                for (Field f : fields) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        if (result.lastIndexOf("[") != result.length() - "[".length()) {
                            result.append(",");
                        }
                        result.append(f.getName());
                        result.append("=");
                        try {
                            Object value = f.get(object);
                            if (f.getType().isPrimitive()) {
                                result.append(value);
                            } else {
                                result.append(toString(value));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                result.append("]");
                cl = cl.getSuperclass();
            } while (cl != null);

        }
        return result.toString();
    }
}
