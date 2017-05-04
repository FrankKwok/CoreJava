package com.github.frankkwok.corejava.v2ch06.formatting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Frank Kwok on 2017/5/4.
 */
public class Formatting {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16,
                9, 32, 0, 0, ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11launch);
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(apollo11launch);

        System.out.println(formatted);

        formatted = formatter.withLocale(Locale.US).format(apollo11launch);
        System.out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(apollo11launch);
        System.out.println(formatted);

        LocalDate turingBirthday = LocalDate.parse("1912-06-23");
        System.out.println("turingBirthday: " + turingBirthday);
        apollo11launch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println("apollo11launch: " + apollo11launch);

        for (DayOfWeek w : DayOfWeek.values()) {
            System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.CHINA) + " ");
        }
    }
}
