package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReversePolishNotationTest {
    @Test
    void forEmptyInput_returnsZero() {
        assertThat(evaluate()).isEqualTo(0);
    }

    @Test
    void forPlainNumber_returnsIt() {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    @Test
    void forSimpleExpression_returnsItsValue() {
        assertThat(evaluate("7", "9", "+")).isEqualTo(16);
        assertThat(evaluate("7", "9", "-")).isEqualTo(-2);
        assertThat(evaluate("7", "2", "*")).isEqualTo(14);
        assertThat(evaluate("7", "3", "/")).isEqualTo(2);
    }

    @Test
    void forCompoundExpression_returnsItsValue() {
        assertThat(evaluate("2", "3", "+", "5", "*")).isEqualTo(25);
        assertThat(evaluate("2", "3", "4", "*", "+", "2", "/")).isEqualTo(7);
    }

    private static int evaluate(String... tokens) {
        return ReversePolishNotation.evaluate(tokens);
    }
}