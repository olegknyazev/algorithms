package com.olegknyazev.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MergeSortTest {
    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertThat(sortInts()).isEmpty();
    }

    @Test
    void returnsSortedCollection() {
        assertThat(sortInts(2, 3, 1)).containsExactly(1, 2, 3);
        assertThat(sortInts(9, 7, 5, 0, -2)).containsExactly(-2, 0, 5, 7, 9);
        assertThat(sortInts(5, -2, 4, 17, 9, 0, 5, 6, -1)).containsExactly(-2, -1, 0, 4, 5, 5, 6, 9, 17);
    }

    @Test
    void sortIsStable() {
        final var _42 = new WrappedInt(42);
        final var _50 = new WrappedInt(50);
        final var _23_1 = new WrappedInt(23);
        final var _23_2 = new WrappedInt(23);
        final var sorted = sortWrappedInts(_50, _23_1, _42, _23_2);
        assertThat(sorted.get(0)).isSameAs(_23_1);
        assertThat(sorted.get(1)).isSameAs(_23_2);
    }

    private static List<Integer> sortInts(Integer... values) {
        return MergeSort.sort(Arrays.asList(values), Comparator.naturalOrder());
    }

    private static List<WrappedInt> sortWrappedInts(WrappedInt... values) {
        return MergeSort.sort(Arrays.asList(values), Comparator.comparingInt(w -> w.value));
    }

    private record WrappedInt(int value) {
    }
}