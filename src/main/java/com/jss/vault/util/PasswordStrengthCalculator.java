package com.jss.vault.util;

import java.util.Arrays;
import java.util.List;

/**
 * The base calculator to know the password strength.
 * */
public class PasswordStrengthCalculator {

    /**
     * Calculates how much strong the password is base on it entropy score. See {@link PasswordEntropyMeasurer}
     *
     * @param password the target password.
     * @return strength of password
     * */
    public static int calculate(final String password) {
        final Regex[] regexArray = Regex.values();
        final List<Regex> matchedRegexList = Arrays.stream(regexArray)
                .filter(regex -> RegexMatcher.match(password, regex))
                .toList();

        final double regexWeight = Arrays.stream(regexArray)
                .map(Regex::getWeight)
                .reduce(.0, Double::sum);

        final double pieceValue = 50 / regexWeight;

        final int totalRange = matchedRegexList.stream()
                .map(Regex::getRange)
                .reduce(0, Integer::sum);

        final double regexScore = matchedRegexList.stream()
                .map(regex -> regex.getWeight() * pieceValue)
                .reduce(0., Double::sum);

        final int entropy = PasswordEntropyMeasurer.measure(password, totalRange);
        final int entropyScore = PasswordEntropyMeasurer.calculateScore(entropy);

        return (int) (entropyScore + regexScore);
    }
}
