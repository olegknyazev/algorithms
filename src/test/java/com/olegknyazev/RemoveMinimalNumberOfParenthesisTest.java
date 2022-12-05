package com.olegknyazev;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.olegknyazev.RemoveMinimalNumberOfParenthesis.removeParenthesis;
import static org.assertj.core.api.Assertions.assertThat;

class RemoveMinimalNumberOfParenthesisTest {
    @Test
    void forInputWithoutParens_returnsTheSameString() {
        assertOutputIsInput(null);
        assertOutputIsInput("");
        assertOutputIsInput("qwe");
    }

    @Test
    void forAlreadyBalancedInput_returnsTheSameString() {
        assertOutputIsInput("a(sd)f");
        assertOutputIsInput("((()))");
        assertOutputIsInput("(a()b(cd(ef))g())");
    }

    @Test
    void forImbalancedInput_returnsStringWithMinimalNumberOfParensRemoved() {
        assertParensRemovalIsOneOf("())", "()");
        assertParensRemovalIsOneOf("(()", "()");
        assertParensRemovalIsOneOf("(a(b(c)d)", "a(b(c)d)", "(ab(c)d)", "(a(bc)d)");
        assertParensRemovalIsOneOf(")()(", "()");
        assertParensRemovalIsOneOf("a)b(c)d(e", "ab(c)de");
        assertParensRemovalIsOneOf("))((", "");
    }

    private static void assertOutputIsInput(String input) {
        assertThat(removeParenthesis(input)).isEqualTo(input);
    }

    private static void assertParensRemovalIsOneOf(String input, String... output) {
        Function<String, Consumer<String>> z = (String x) -> (String y) -> assertThat(y).isEqualTo(x);
        assertThat(removeParenthesis(input))
                .satisfiesAnyOf(Arrays.stream(output).map(z).toArray(Consumer[]::new));
    }
}