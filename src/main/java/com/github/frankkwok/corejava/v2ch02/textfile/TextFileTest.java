package com.github.frankkwok.corejava.v2ch02.textfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/20.
 */
public class TextFileTest {
    private static final String FILE_NAME = "employee_text_file.dat";

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (PrintWriter out = new PrintWriter(FILE_NAME, "UTF-8")) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner(new FileInputStream(FILE_NAME), "UTF-8")) {
            Employee[] newStaff = readData(in);

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        for (Employee e : employees) {
            writeEmployee(e, out);
        }
    }

    private static void writeEmployee(Employee e, PrintWriter out) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }

    private static Employee[] readData(Scanner in) {
        int length = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[length];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDay = LocalDate.parse(tokens[2]);
        int year = hireDay.getYear();
        int month = hireDay.getMonthValue();
        int dayOfMonth = hireDay.getDayOfMonth();
        return new Employee(name, salary, year, month, dayOfMonth);
    }
}
