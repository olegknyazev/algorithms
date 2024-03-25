package com.olegknyazev.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MergeSortTest extends SortTestBase {
    @Override
    protected <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        return MergeSort.sort(collection, comparator);
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

    private List<WrappedInt> sortWrappedInts(WrappedInt... values) {
        return sort(Arrays.asList(values), Comparator.comparingInt(w -> w.value));
    }

    private record WrappedInt(int value) {
    }
}