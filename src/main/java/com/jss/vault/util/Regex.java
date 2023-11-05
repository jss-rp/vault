package com.jss.vault.util;


import lombok.Getter;

@Getter
public enum Regex {
    UPPER_CASE("[A-Z]", 28, 1),
    LOWER_CASE("[a-z]", 28, 1),
    NUMERALS("[0-9]", 28, 1),
    SPECIAL_CHARACTERS("[~!@#$%^&*()_+{}\\[\\]:;,.<>/?-]", 30, 2);

    private final String regex;
    private final int range;
    private final double weight;

    Regex(final String regex, final int range, final int weight) {
        this.regex = regex;
        this.range = range;
        this.weight = weight;
    }
}
