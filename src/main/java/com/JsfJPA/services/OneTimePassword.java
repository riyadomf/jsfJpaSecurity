package com.JsfJPA.services;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class OneTimePassword {
    private static final Logger logger = Logger.getLogger(OneTimePassword.class.getName());

    public static final String SECRET32 = "secret32";
    public static final String QR_URL = "url";
    public static final String SECRET = "secret";
    public static final String OTPAUTH = "otpauth";

    public static final int WINDOW = 3;

    public static final String GURL = "https://www.google.com/chart?chs=250x250&cht=qr&chl=otpauth://totp/";

    private final static SecureRandom random = new SecureRandom();

    //From http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();


    public static Map<String, Integer> generateQR(String subject, String issuer) {

        Integer secret = 20000;
        random.nextInt(secret);
        Map<String, Integer> result = new HashMap<>();
        result.put(SECRET32, secret);
        result.put(SECRET, secret);

        return (result);

    }


    public static boolean checkCode(Integer secret, Integer code) {
        return secret.equals(code);
    }


}
