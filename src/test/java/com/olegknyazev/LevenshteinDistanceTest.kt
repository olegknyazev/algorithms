package com.olegknyazev

import com.olegknyazev.LevenshteinDistance.levenshteinDistance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LevenshteinDistanceTest {
    @Test
    fun `degenerated cases`() {
        assertThat(levenshteinDistance("", "")).isEqualTo(0)
        assertThat(levenshteinDistance("abcd", "")).isEqualTo(4)
        assertThat(levenshteinDistance("", "abcd")).isEqualTo(4)
    }

    @Test
    fun `regular cases`() {
        assertThat(levenshteinDistance("abcd", "bcy")).isEqualTo(2)
        assertThat(levenshteinDistance("Hello, world!", "Hello, rabbit!")).isEqualTo(6)
    }
}