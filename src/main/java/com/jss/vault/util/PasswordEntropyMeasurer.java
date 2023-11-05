package com.jss.vault.util;

public class PasswordEntropyMeasurer {

    public static int measure(final String password, final int totalRange) {
        final double a = Math.pow(totalRange, password.length());

        return (int) (Math.log(a) / Math.log(2));
    }

    public static int calculateScore(final int entropy) {
        int entropyScore = 0;

        entropyScore += (entropy > 49 && entropy < 75) ? 50 : 0;
        entropyScore += (entropy > 74) ? 50 : 0;

        return entropyScore;
    }
}
