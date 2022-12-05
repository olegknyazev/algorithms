package com.olegknyazev;

import java.util.HashSet;
import java.util.Stack;

public class RemoveMinimalNumberOfParenthesis {
    private RemoveMinimalNumberOfParenthesis() {
        throw new AssertionError();
    }

    // O(n) runtime, O(n) memory
    public static String removeParenthesis(String input) {
        if (input == null)
            return null;
        var parensPositions = new Stack<Integer>();
        var positionsToRemove = new HashSet<Integer>();
        for (int i = 0; i < input.length(); ++i) {
            final var ch = input.charAt(i);
            if (ch == '(')
                parensPositions.add(i);
            else if (ch == ')') {
                if (parensPositions.isEmpty())
                    positionsToRemove.add(i);
                else
                    parensPositions.pop();
            }
        }
        positionsToRemove.addAll(parensPositions);
        var output = new StringBuilder();
        for (int i = 0; i < input.length(); ++i)
            if (!positionsToRemove.contains(i))
                output.append(input.charAt(i));
        return output.toString();
    }
}
