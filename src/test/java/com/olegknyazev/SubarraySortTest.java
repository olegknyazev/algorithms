package com.olegknyazev;

import com.olegknyazev.SubarraySort.Range;
import org.junit.jupiter.api.Test;

import static com.olegknyazev.SubarraySort.NO_RANGE;
import static com.olegknyazev.SubarraySort.unsortedSubarray;
import static org.assertj.core.api.Assertions.assertThat;

class SubarraySortTest {
    @Test
    void forArrayWithLessThanTwoItems_returnsThatItIsAlreadySorted() {
        assertThat(unsortedSubarray(new int[] { })).isEqualTo(NO_RANGE);
        assertThat(unsortedSubarray(new int[] { 1 })).isEqualTo(NO_RANGE);
    }

    @Test
    void forAlreadySortedArray_returnsThatItIsAlreadySorted() {
        assertThat(unsortedSubarray(new int[] { 1, 2 })).isEqualTo(NO_RANGE);
        assertThat(unsortedSubarray(new int[] { 1, 2, 3 })).isEqualTo(NO_RANGE);
        assertThat(unsortedSubarray(new int[] { -3, 8, 19, 100 })).isEqualTo(NO_RANGE);
    }

    @Test
    void forUnsortedArray_returnsTheLongestStripe() {
        assertThat(unsortedSubarray(new int[] { 1, 3, 2 })).isEqualTo(new Range(1, 2));
        assertThat(unsortedSubarray(new int[] { 3, 2, 1 })).isEqualTo(new Range(0, 2));
        assertThat(unsortedSubarray(new int[] { 1, 4, 3, 2, 5 })).isEqualTo(new Range(1, 3));
        assertThat(unsortedSubarray(new int[] { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 })).isEqualTo(new Range(3, 9));
    }
}