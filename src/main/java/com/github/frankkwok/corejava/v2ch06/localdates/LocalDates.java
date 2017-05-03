package com.github.frankkwok.corejava.v2ch06.localdates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author Frank Kwok on 2017/5/3.
 */
public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today: " + today);

        LocalDate turingBirthday = LocalDate.of(1912, 6, 23);
        turingBirthday = LocalDate.of(1912, Month.JUNE, 23);

        System.out.println("turingBirthday: " + turingBirthday);

        LocalDate programmersDay = LocalDate.of(2017, 1, 1).plusDays(255);
        System.out.println("programmersDay: " + programmersDay);

        LocalDate nationalDay = LocalDate.of(2017, 10, 1);
        LocalDate christmasDay = LocalDate.of(2017, Month.DECEMBER, 25);

        System.out.println("Util christmas: " + nationalDay.until(christmasDay));
        System.out.println("Util christmas: " + nationalDay.until(christmasDay, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2016, 1, 31).plusMonths(1));
        System.out.println(LocalDate.of(2016, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1990, 1, 1).getDayOfWeek();
        System.out.println("startOfLastMillennium: " + startOfLastMillennium);
        System.out.println(startOfLastMillennium.getValue());
        System.out.println(DayOfWeek.SATURDAY.plus(3));
    }
}
