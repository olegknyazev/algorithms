package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.MergeLinkedLists.mergeLists;
import static org.assertj.core.api.Assertions.assertThat;

class MergeLinkedListsTest {
    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertThat(mergeLists(MutableSinglyLinkedList.of(), MutableSinglyLinkedList.of())).isNull();
    }

    @Test
    void ifOneOfTheListsIsEmpty_returnsTheOther() {
        final var list = MutableSinglyLinkedList.of(1, 2, 3);
        assertThat(mergeLists(list, MutableSinglyLinkedList.of())).isSameAs(list).isEqualTo(list);
        assertThat(mergeLists(MutableSinglyLinkedList.of(), list)).isSameAs(list).isEqualTo(list);
    }

    @Test
    void forTwoArbitraryLists_returnsAMergedList() {
        final var a = MutableSinglyLinkedList.of(2, 6, 7, 8);
        final var b = MutableSinglyLinkedList.of(1, 3, 4, 5, 9, 10);
        final var merged = mergeLists(a, b);
        assertThat(merged).isSameAs(b);
        assertThat(merged).isEqualTo(MutableSinglyLinkedList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}