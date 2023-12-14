package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.StringPatternMatching.matchPattern;
import static org.assertj.core.api.Assertions.assertThat;

class StringPatternMatchingTest {
    @Test
    void forEmptyString_returnsEmptyArray() {
        assertThat(matchPattern("xy", "")).isEmpty();
    }

    @Test
    void forNonMatchingString_returnsEmptyArray() {
        assertThat(matchPattern("xyy", "kek")).isEmpty();
    }

    @Test
    void forMatchingString_returnsSubstitutions() {
        assertThat(matchPattern("xyx", "abcqabc")).containsExactly("abc", "q");
        assertThat(matchPattern("xyxy", "abab")).containsExactly("a", "b");
        assertThat(matchPattern("xxyxxy", "gogopowerrangergogopowerranger")).containsExactly("go", "powerranger");
        assertThat(matchPattern("xxx", "testtesttest")).containsExactly("test", "");
        assertThat(matchPattern("yyy", "testtesttest")).containsExactly("", "test");
    }
}