package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.ReverseLinkedList.reverseInPlace;
import static org.assertj.core.api.Assertions.assertThat;

class ReverseLinkedListTest {
    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertThat(reverseInPlace(newListOf())).isNull();
    }

    @Test
    void forSingleElementInput_returnsTheSameList() {
        final var list = newListOf(1);
        assertThat(reverseInPlace(list)).isSameAs(list);
    }

    @Test
    void forRegularLists_returnsReversedVersions() {
        assertThat(reverseInPlace(newListOf(1, 2, 3)).toList()).containsExactly(3, 2, 1);
        assertThat(reverseInPlace(newListOf(17, -2, 6, 9, 0)).toList()).containsExactly(0, 9, 6, -2, 17);
    }

    private static MutableSinglyLinkedList newListOf(int... values) {
        return MutableSinglyLinkedList.of(values);
    }
}