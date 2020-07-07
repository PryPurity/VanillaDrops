package com.github.prypurity.vanilladrops.utils;

public abstract class Utils {

    public static String titleCase(String delimiter, String yes) {
        String[] ar = yes.split(delimiter);
        StringBuilder builder = new StringBuilder(delimiter);
        for (String s : ar) {
            builder.append(caps(s));
        }
        return builder.toString();
    }

    public static String caps(String s) {
        if (s.isEmpty()) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
