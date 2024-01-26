package com.eappeal.report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.util.Locale;

public class DateUtils {
    public static String getLocalizedDateTime(LocalDateTime localDateTime, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDateTime.format(formatter);
    }


    public static String getLocalizedDate(LocalDate localDate, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDate.format(formatter);
    }

    public static String getLocalizedTime(LocalTime localTime, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("hh:mm a")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localTime.format(formatter);
    }


    public static String getLocalizedDateFromTimeStamp(LocalDateTime localDateTime, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd MMM , uu")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDateTime.format(formatter);
    }

    public static LocalDateTime addDaysToCurrentDate (Long numOfDays){
        return LocalDateTime.now().plusDays(numOfDays);
    }

    public static String getLocalizedDayOfMonth(LocalDate localDate, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDate.format(formatter);
    }

    public static String getLocalizedMonthValue(LocalDate localDate, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("MM")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDate.format(formatter);
    }

    public static String getLocalizedYear(LocalDate localDate, Locale locale) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDate.format(formatter);
    }
}