package com.olegknyazev;

import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ReversePolishNotation {
    // Evaluates the expression presented as a list of tokens.
    // The supported tokens are +, -, *, / and numbers.
    // O(n) runtime, O(n) memory
    public static int evaluate(String[] tokens) {
        if (tokens.length == 0)
            return 0;
        final var stack = new Stack<Integer>();
        final Consumer<BiFunction<Integer, Integer, Integer>> withTwoOperands = (operation) -> {
            final var second = stack.pop();
            final var first = stack.pop();
            stack.push(operation.apply(first, second));
        };
        for (var token : tokens)
            switch (token) {
                case "+":
                    withTwoOperands.accept((a, b) -> a + b);
                    break;
                case "-":
                    withTwoOperands.accept((a, b) -> a - b);
                    break;
                case "*":
                    withTwoOperands.accept((a, b) -> a * b);
                    break;
                case "/":
                    withTwoOperands.accept((a, b) -> a / b);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        return stack.pop();
    }
}
