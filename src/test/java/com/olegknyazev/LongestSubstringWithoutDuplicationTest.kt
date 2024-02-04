package com.olegknyazev

import com.olegknyazev.LongestSubstringWithoutDuplication.longestSubstringWithoutDuplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LongestSubstringWithoutDuplicationTest {
    @Test
    fun `for empty input returns empty output`() {
        assertThat(longestSubstringWithoutDuplication("")).isEmpty()
    }

    @Test
    fun `for a non-empty string returns the longest substring without duplication`() {
        assertThat(longestSubstringWithoutDuplication("clementisacap")).isEqualTo("mentisac")
        assertThat(longestSubstringWithoutDuplication("abcdef")).isEqualTo("abcdef")
        assertThat(longestSubstringWithoutDuplication("ababcdabc")).isEqualTo("abcd")
    }
}