package org.sample;

import java.util.List;

public class ListToStringBuilder {

    public static String toStringIfFirst(List<String> vars) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, size = vars.size(); i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(vars.get(i));
        }
        return result.toString();

    }

    public static String toStringIfLast(List<String> vars) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, size = vars.size(); i < size; i++) {
            result.append(vars.get(i));
            if (i < vars.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public static String toStringSeparatorChar(List<String> vars) {
        String separator = "";
        StringBuilder result = new StringBuilder();
        for (int i = 0, size = vars.size(); i < size; i++) {
            result.append(separator);
            result.append(vars.get(i));
            separator = ", ";
        }
        return result.toString();

    }

    public static String toStringSeparatorCharForEach(List<String> vars) {
        String separator = "";
        StringBuilder result = new StringBuilder();
        for (String var: vars) {
            result.append(separator);
            result.append(var);
            separator = ", ";
        }
        return result.toString();

    }
}
