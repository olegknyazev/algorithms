package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.SingleCycleCheck.hasSingleCycle;
import static org.assertj.core.api.Assertions.assertThat;

class SingleCycleCheckTest {
    @Test
    void forEmptyArray_returnsFalse() {
        assertThat(hasSingleCycle(new int[] {})).isFalse();
    }

    @Test
    void forCycledArrays_returnsTrue() {
        assertThat(hasSingleCycle(new int[] { 1 })).isTrue();
        assertThat(hasSingleCycle(new int[] { 1, 1, 1 })).isTrue();
        assertThat(hasSingleCycle(new int[] { 4, 2, -2, -1, -3 })).isTrue();
        assertThat(hasSingleCycle(new int[] { 2, 3, 1, -4, -4, 2 })).isTrue();
        assertThat(hasSingleCycle(new int[] { 1, 2, -2, -1 })).isTrue();
    }

    @Test
    void forNonCycledArrays_returnsFalse() {
        assertThat(hasSingleCycle(new int[] { 2, -1 })).isFalse();
        assertThat(hasSingleCycle(new int[] { 1, 1, 0 })).isFalse();
        assertThat(hasSingleCycle(new int[] { 1, 2, -1, -1 })).isFalse();
        assertThat(hasSingleCycle(new int[] { 1, -1, 1, -1 })).isFalse();
    }
}