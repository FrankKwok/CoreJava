package com.github.frankkwok.corejava.v2ch02.objectstream;

import java.io.*;

/**
 * @author Frank Kwok on 2017/4/26.
 */
public class ObjectStreamTest {
    private static final String FILE_NAME = "employee_object_stream.dat";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee carl = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        Manager harry = new Manager("Harry Hacker", 50000, 1989, 10, 1);
        harry.setSecretary(carl);
        Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
        tony.setSecretary(carl);

        Employee[] staff = new Employee[]{
                carl,
                harry,
                tony
        };

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Employee[] newStaff = (Employee[]) in.readObject();
            newStaff[0].raiseSalary(5);
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }
}
