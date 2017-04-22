package com.github.frankkwok.corejava.v2ch02.randomaccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

import static com.github.frankkwok.corejava.v2ch02.randomaccess.DataIO.readFixedString;
import static com.github.frankkwok.corejava.v2ch02.randomaccess.DataIO.writeFixedString;

/**
 * @author Frank Kwok on 2017/4/20.
 */
public class RandomAccessTest {
    private static final String FILE_NAME = "employee_random_access.dat";

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (RandomAccessFile output = new RandomAccessFile(FILE_NAME, "rw")) {
            for (Employee e : staff) {
                writeEmployee(e, output);
            }
        }

        try (RandomAccessFile input = new RandomAccessFile(FILE_NAME, "r")) {
            int length = (int) (input.length() / Employee.RECORD_SIZE);
            Employee[] newStaff = new Employee[length];
            for (int i = length - 1; i >= 0; i--) {
                input.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readEmployee(input);
            }

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    private static void writeEmployee(Employee e, DataOutput output) throws IOException {
        writeFixedString(e.getName(), Employee.NAME_SIZE, output);
        output.writeDouble(e.getSalary());
        LocalDate hireDay = e.getHireDay();
        output.writeInt(hireDay.getYear());
        output.writeInt(hireDay.getMonthValue());
        output.writeInt(hireDay.getDayOfMonth());
    }

    private static Employee readEmployee(DataInput input) throws IOException {
        String name = readFixedString(Employee.NAME_SIZE, input);
        double salary = input.readDouble();
        int year = input.readInt();
        int month = input.readInt();
        int dayOfMonth = input.readInt();
        return new Employee(name, salary, year, month, dayOfMonth);
    }
}
