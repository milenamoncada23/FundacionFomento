package com.fundacionfomento.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class Hashing {
    public static String sha256(String s) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : messageDigest.digest(s.getBytes(StandardCharsets.UTF_8)))
                hexStringBuilder.append(String.format("%02x", b));
            return hexStringBuilder.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
