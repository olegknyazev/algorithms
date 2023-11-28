package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LargestRangeTest {
    @Test
    void forEmptyInput_returnsEmptyArray() {
        assertThat(largestRange()).isEmpty();
    }

    @Test
    void forSingleElementInput_returnsTheSameElementTwice() {
        assertThat(largestRange(5)).containsExactly(5, 5);
    }

    @Test
    void forRegularInput_returnsTheLargestRange() {
        assertThat(largestRange(-2, 4, 3, 17, -1, 5)).containsExactly(3, 5);
        assertThat(largestRange(1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6)).containsExactly(0, 7);
        assertThat(largestRange(0, 9, 19, -1, 18, 17, 2, 10, 3, 12, 5, 16, 4, 11, 8, 7, 6, 15, 12, 12, 2, 1, 6, 13, 14))
                .containsExactly(-1, 19);
    }

    private static int[] largestRange(int... args) {
        return LargestRange.largestRange(args);
    }
}