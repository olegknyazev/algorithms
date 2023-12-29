package com.olegknyazev;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequence {
    // Returns the longest common subsequence of two given strings. A subsequence is
    // any sequence of characters from the original string in order of occurrence, for
    // example "abd" is a subsequence of the string "abcd".
    // O(n^2) runtime, O(n^2) memory
    public static List<Character> longestCommonSubsequence(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty())
            return new ArrayList<>();
        final var data = new Data[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); ++i)
            for (int j = 0; j < str2.length(); ++j)
                data[i][j] = new Data();
        for (int i = 0; i < str1.length(); ++i)
            for (int j = 0; j < str2.length(); ++j) {
                final var cell = data[i][j];
                final var option1 = i > 0 ? data[i - 1][j] : Data.EMPTY;
                final var option2 = j > 0 ? data[i][j - 1] : Data.EMPTY;
                final var bestOption = option1.length >= option2.length ? option1 : option2;
                cell.length = bestOption.length;
                cell.lastUsed = bestOption.lastUsed;
                cell.comeFrom = bestOption == option1 ? new Coordinates(i - 1, j) : new Coordinates(i, j - 1);
                final var matches = str1.charAt(i) == str2.charAt(j);
                final var charIsFree = bestOption.lastUsed.j < j && bestOption.lastUsed.i < i;
                if (matches && charIsFree) {
                    cell.length += 1;
                    cell.lastUsed = new Coordinates(i, j);
                    cell.ch = str1.charAt(i);
                }
            }
        final var result = new ArrayList<Character>();
        var coordinates = new Coordinates(str1.length() - 1, str2.length() - 1);
        do {
            var cell = data[coordinates.i][coordinates.j];
            if (cell.ch != null)
                result.add(0, cell.ch);
            coordinates = cell.comeFrom;
        } while (coordinates.i != -1 && coordinates.j != -1);
        return result;
    }

    private static class Data {
        int length = 0;
        Coordinates lastUsed = Coordinates.MINUS_ONE;
        Coordinates comeFrom = Coordinates.MINUS_ONE;
        Character ch = null;

        static Data EMPTY = new Data();
    }

    private record Coordinates(int i, int j) {
        static Coordinates MINUS_ONE = new Coordinates(-1, -1);
    }
}
