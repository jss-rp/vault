package com.jss.vault.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static boolean match(final String target, final Regex regex) {
        final Pattern patter = Pattern.compile(regex.getRegex());
        final Matcher matcher = patter.matcher(target);

        return matcher.find();
    }
}
