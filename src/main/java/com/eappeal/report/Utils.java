package com.eappeal.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    public static String banglaMoneyFormatter(BigDecimal amount) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(amount);
    }

//    public static String banglaMoneyFormatter(BigDecimal amount) {
//        String formattedAmount = amount.setScale(2, RoundingMode.HALF_DOWN).toString();
//        return addCommas(formattedAmount);
//    }

//    private static String addCommas(String input) {
//        StringBuilder result = new StringBuilder();
//        String[] parts = input.split("\\.");
//        String integerPart = parts[0];
//
//        for (int i = 0; i < integerPart.length(); i++) {
//            char c = integerPart.charAt(i);
//            result.append(c);
//
//            int positionFromRight = integerPart.length() - 1 - i;
//            if (positionFromRight > 0 && positionFromRight % 2 == 0) {
//                result.append(",");
//            }
//        }
//
//        // Add the decimal part if present
//        if (parts.length > 1) {
//            result.append(".").append(parts[1]);
//        }
//
//        return result.toString();
//    }
}






