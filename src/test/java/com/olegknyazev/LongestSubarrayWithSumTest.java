package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.LongestSubarrayWithSum.longestSubarrayWithSum;
import static org.assertj.core.api.Assertions.assertThat;

class LongestSubarrayWithSumTest {
    @Test
    void forEmptyInput_returnsEmptyArray() {
        assertThat(longestSubarrayWithSum(new int[]{}, 5)).isEmpty();
    }

    @Test
    void forInputNotContainingSubarrayWithTheGivenSum_returnsEmptyArray() {
        assertThat(longestSubarrayWithSum(new int[]{2, 2, 2, 2}, 3)).isEmpty();
        assertThat(longestSubarrayWithSum(new int[]{1, 4, 10, 15, 31}, 0)).isEmpty();
    }

    @Test
    void forInputContainingSubarraysWithTheGivenSum_returnsLongestOfThem() {
        assertThat(longestSubarrayWithSum(new int[]{10}, 10)).isEqualTo(new int[]{0, 0});
        assertThat(longestSubarrayWithSum(new int[]{1, 2, 3, 4, 3, 3, 1, 2, 1, 2}, 10)).isEqualTo(new int[]{4, 8});
        assertThat(longestSubarrayWithSum(new int[]{1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10}, 15)).isEqualTo(new int[]{0, 7});
    }
}