package com.olegknyazev.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


abstract class SortTestBase {
    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertThat(sortInts()).isEmpty();
    }

    @Test
    void returnsSortedCollection() {
        assertThat(sortInts(2, 3, 1)).containsExactly(1, 2, 3);
        assertThat(sortInts(9, 7, 5, 0, -2)).containsExactly(-2, 0, 5, 7, 9);
        assertThat(sortInts(5, -2, 4, 17, 9, 0, 5, 6, -1)).containsExactly(-2, -1, 0, 4, 5, 5, 6, 9, 17);
        assertThat(sortInts(1, 10, 3, 13)).containsExactly(1, 3, 10, 13);
        assertThat(sortInts(1, 10, 13, 3)).containsExactly(1, 3, 10, 13);
        assertThat(sortInts(5, 5, 5)).containsExactly(5, 5, 5);
    }

    protected abstract <T> List<T> sort(Collection<T> collection, Comparator<T> comparator);

    private List<Integer> sortInts(Integer... values) {
        return sort(Arrays.asList(values), Comparator.naturalOrder());
    }
}