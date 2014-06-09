package com.mlefevre.engine.rule.util;

public class ObjectParser {


    public static Object parse(Object value) {
        String val = toString(value);
        Object res = parseString(val);

        return res;
    }

    public static Object parseString(String value) {
        Object res = value;

        try {
            res = Double.valueOf(value);
        } catch(NumberFormatException e) {
        }
        try {
            res = Float.valueOf(value);
        } catch(NumberFormatException e) {
        }
        try {
            res = Integer.valueOf(value);
        } catch(NumberFormatException e) {
        }

        return res;
    }

    public static String toString(Object value) {
        if(value == null) {
            throw new IllegalArgumentException("No value to parse given.");
        }
        String result = null;

        if(value instanceof Integer) {
            result = Integer.toString((Integer) value);
        } else if(value instanceof Double) {
            result = Double.toString((Double) value);
        } else if(value instanceof Float) {
            result = Float.toString((Float) value);
        } else if(value instanceof String) {
            result = (String) value;
        } else if(value instanceof Boolean) {
            result = Boolean.toString((Boolean) value);
        }

        return result;
    }


}