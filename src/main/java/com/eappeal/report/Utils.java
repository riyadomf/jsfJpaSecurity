package com.eappeal.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.util.Locale;

public class Utils {
    public static String englishToBanglaDigitConversion(String englishNumber) {
        if (englishNumber == null || englishNumber.isEmpty())
            return "";
        StringBuilder banglaNumber = new StringBuilder();

        char[] banglaDigits = {'০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'};

        try {
            for (char digit : englishNumber.toCharArray()) {
                if (Character.isDigit(digit)) {
                    if ((digit - 48) <= 9) {
                        banglaNumber.append(banglaDigits[digit - 48]);
                    } else {
                        banglaNumber.append(digit);
                    }
                } else {
                    banglaNumber.append(digit);
                }
            }
        } catch (Exception e) {
            // Add logger
            return "";
        }
        return banglaNumber.toString();
    }

//    public static String banglaMoneyFormatter(BigDecimal amount) {
//        DecimalFormat df = new DecimalFormat("###,##0.00");
//        return df.format(amount);
//    }

    public static String banglaMoneyFormatter(BigDecimal amount) {
        String formattedAmount = amount.setScale(2, RoundingMode.HALF_DOWN).toString();
        return banglaMoneyCommaFormatter(formattedAmount);
    }

    private static String banglaMoneyCommaFormatter(String amount) {
        StringBuilder result = new StringBuilder();
        String[] parts = amount.split("\\.");
        String integerPart = parts[0];

        for (int i = 0; i < integerPart.length(); i++) {
            char c = integerPart.charAt(i);
            result.append(c);

            int positionFromRight = integerPart.length() - 1 - i;
            int positionInGroup = positionFromRight % 7;
            if (positionFromRight > 0 && positionInGroup == 0 || positionInGroup == 3 || positionInGroup == 5) {
                result.append(",");
            }
        }

        // Add the decimal part if present
        if (parts.length > 1) {
            result.append(".").append(parts[1]);
        }

        return result.toString();
    }

    public static String getBanglaDate(LocalDate localDate) {
        Locale locale = new Locale.Builder().setLanguage("bn").build();
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .localizedBy(locale)
                .withDecimalStyle(DecimalStyle.of(locale));

        return localDate.format(formatter);
    }
}






