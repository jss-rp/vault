package com.jss.vault.util;

/**
 * Class responsible for calculate the password entropy
 * */
public class PasswordEntropyMeasurer {

    /**
     * Measures how much the password is strong based on the simple entropy calculus.
     * The formula is the following:
     * <br/>
     * <p>
     *     E = L × log2(R)
     * </p>
     * <br/>
     * Where E = Entropy, L = size of password and R = total of possible Range in password
     *
     * @param password the target password
     * @param totalRange sum of all total ranges
     * @return entropy measure
     * */
    public static int measure(final String password, final int totalRange) {
        final double a = Math.pow(totalRange, password.length());

        return (int) (Math.log(a) / Math.log(2));
    }

    /**
     * Calculate how much the entropy is good based on range 0 to 100
     *
     * @param entropy measure of
     * @return score of entropy (0 to 100)
     * */
    public static int calculateScore(final int entropy) {
        int entropyScore = 0;

        entropyScore += (entropy > 49 && entropy < 75) ? 50 : 0;
        entropyScore += (entropy > 74) ? 50 : 0;

        return entropyScore;
    }
}
