package com.eappeal.report;

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

}






