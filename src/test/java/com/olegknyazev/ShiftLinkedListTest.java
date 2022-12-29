package com.olegknyazev;

import com.olegknyazev.ShiftLinkedList.MutableNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static com.olegknyazev.ShiftLinkedList.newListOf;
import static com.olegknyazev.ShiftLinkedList.shiftInPlace;

class ShiftLinkedListTest {
    @Test
    void forEmptyInput_doesNothing() {
        shiftInPlace(newListOf(), 0);
        shiftInPlace(newListOf(), 1);
        shiftInPlace(newListOf(), -1);
        // should not throw
    }

    @Test
    void forZeroOffset_doesNothing() {
        assertShiftedIsTheSame(newListOf(1), 0);
        assertShiftedIsTheSame(newListOf(1, -2, 3), 0);
    }

    @Test
    void forPositiveOffset_movesListForwards() {
        assertShiftedIs(newListOf(1, 2, 3), 1, List.of(3, 1, 2));
        assertShiftedIs(newListOf(1, 2, 3), 2, List.of(2, 3, 1));
        assertShiftedIs(newListOf(1, 2, 3), 3, List.of(1, 2, 3));
        assertShiftedIs(newListOf(1, 2, 3), 4, List.of(3, 1, 2));
    }

    @Test
    void forNegativeOffset_movesListBackwards() {
        assertShiftedIs(newListOf(1, 2, 3), -1, List.of(2, 3, 1));
        assertShiftedIs(newListOf(1, 2, 3), -2, List.of(3, 1, 2));
        assertShiftedIs(newListOf(1, 2, 3), -3, List.of(1, 2, 3));
        assertShiftedIs(newListOf(1, 2, 3), -4, List.of(2, 3, 1));
    }

    private static void assertShiftedIs(MutableNode original, int offset, List<Integer> expected) {
        var shifted = shiftInPlace(original, offset);
        assertThat(shifted.toList()).isEqualTo(expected);
    }

    private static void assertShiftedIsTheSame(MutableNode original, int offset) {
        var expected = original.toList();
        assertShiftedIs(original, offset, expected);
    }
}