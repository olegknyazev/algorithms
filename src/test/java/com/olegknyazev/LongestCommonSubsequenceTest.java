package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.LongestCommonSubsequence.longestCommonSubsequence;
import static org.assertj.core.api.Assertions.assertThat;

class LongestCommonSubsequenceTest {
    @Test
    void forEmptyStrings_returnsEmptySequence() {
        assertThat(longestCommonSubsequence("", "")).isEmpty();
    }

    @Test
    void forNonIntersectingStrings_returnsEmptySequence() {
        assertThat(longestCommonSubsequence("abc", "xyz")).isEmpty();
    }

    @Test
    void forIntersectingStrings_returnsTheLongestSubsequence() {
//        assertThat(longestCommonSubsequence("abcde", "aebd")).containsExactly('a', 'b', 'd');
//        assertThat(longestCommonSubsequence("zxvvyzw", "xkykzpw")).containsExactly('x', 'y', 'z', 'w');
        assertThat(longestCommonSubsequence("8111111111111111142", "222222222822222222222222222222433333333332")).containsExactly('8', '4', '2');
    }

    @Test
    void forEqualStrings_returnsTheEntireString() {
        assertThat(longestCommonSubsequence("abcd", "abcd")).containsExactly('a', 'b', 'c', 'd');
    }
}