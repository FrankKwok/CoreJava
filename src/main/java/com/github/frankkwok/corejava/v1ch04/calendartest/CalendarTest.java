package com.github.frankkwok.corejava.v1ch04.calendartest;

import java.time.LocalDate;

/**
 * This program print the calendar view of current month.
 *
 * @author Frank Kwok on 2017/3/29.
 */
public class CalendarTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        int today = date.getDayOfMonth();
        int month = date.getMonthValue();

        // set to month first day
        date = date.minusDays(today - 1);

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");

        for (int i = 1; i < date.getDayOfWeek().getValue(); i++) {
            System.out.print("    ");
        }

        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }
    }
}
