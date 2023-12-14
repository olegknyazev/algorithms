package com.olegknyazev;

import java.util.stream.IntStream;

public class StringPatternMatching {
    // The function takes pattern and string, and returns a two-element array if the
    // string matches the pattern and empty array otherwise. The pattern may contain
    // only characters 'x' and 'y'. The string matches the pattern, if both 'x' and
    // 'y' in the pattern may be replaced with some substrings of string so that the
    // result equals the original string.
    // O(N^2) runtime, O(N) memory
    public static String[] matchPattern(String pattern, String string) {
        final var countX = countChars(pattern, 'x');
        final var countY = countChars(pattern, 'y');
        for (int xLength = 0; xLength < string.length(); ++xLength) {
            if (xLength * countX > string.length())
                break;
            if (countX != 0 && xLength == 0)
                continue;
            final var remainingLength = string.length() - xLength * countX;
            if (countY != 0 && remainingLength % countY != 0)
                continue;
            final var yLength = countY != 0 ? remainingLength / countY : 0;
            final var xSubstring = extractSubstring(string, pattern, 'x', xLength, yLength);
            final var ySubstring = extractSubstring(string, pattern, 'y', yLength, xLength);
            if (pattern.replaceAll("x", xSubstring).replaceAll("y", ySubstring).equals(string))
                return new String[]{xSubstring, ySubstring};
        }
        return new String[0];
    }

    private static int countChars(String string, char ch) {
        return (int) IntStream.range(0, string.length()).filter(i -> string.charAt(i) == ch).count();
    }

    private static String extractSubstring(String string, String pattern, char symbol, int thisLength, int otherLength) {
        final var index = pattern.indexOf(symbol);
        return index != -1 ? string.substring(index * otherLength, index * otherLength + thisLength) : "";
    }
}
