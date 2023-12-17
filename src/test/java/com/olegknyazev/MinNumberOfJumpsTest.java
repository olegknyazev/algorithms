package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinNumberOfJumpsTest {
    @Test
    void forEmptyArray_returnsZero() {
        assertThat(minNumberOfJumps()).isEqualTo(0);
    }

    @Test
    void forSingleElementArray_returnsZero() {
        assertThat(minNumberOfJumps(1)).isEqualTo(0);
        assertThat(minNumberOfJumps(2)).isEqualTo(0);
    }

    @Test
    void forRegularArrays_returnsTheMinimumNumberOfJumps() {
        assertThat(minNumberOfJumps(2, 4, 1, 3, 2, 1)).isEqualTo(2); // 2 -> 4 -> 1
        assertThat(minNumberOfJumps(2, 3, 1, 3, 2, 1, 2)).isEqualTo(3); // 2 -> 3 -> 3 -> 2
        assertThat(minNumberOfJumps(3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3)).isEqualTo(4); // 3 -> 4 -> 3 -> 7 -> 3
    }

    private static int minNumberOfJumps(int... array) {
        return MinNumberOfJumps.minNumberOfJumps(array);
    }
}