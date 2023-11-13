package com.olegknyazev;

import com.olegknyazev.SinglyLinkedListLoop.SinglyLinkedList;
import org.junit.jupiter.api.Test;

import static com.olegknyazev.SinglyLinkedListLoop.findLoop;
import static org.assertj.core.api.Assertions.assertThat;

class SinglyLinkedListLoopTest {
    @Test
    void forLinkedListWithoutALoop_returnsNull() {
        assertThat(findLoop(SinglyLinkedList.of())).isNull();
        assertThat(findLoop(SinglyLinkedList.of(1))).isNull();
        assertThat(findLoop(SinglyLinkedList.of(1, 2, 3))).isNull();
    }

    @Test
    void forLinkedListWithALoop_returnsTheNodeFromWhichTheLoopOriginates_case1() {
        var list = SinglyLinkedList.of(1, 2, 3, 4, 5);
        setLoop(list, 4, 2); // 5 -> 3
        assertThat(findLoop(list)).isSameAs(list.nth(2));
    }

    @Test
    void forLinkedListWithALoop_returnsTheNodeFromWhichTheLoopOriginates_case2() {
        var list = SinglyLinkedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        setLoop(list, 9, 4); // 9 -> 4
        assertThat(findLoop(list)).isSameAs(list.nth(4));
    }

    @Test
    void forLinkedListWithALoop_returnsTheNodeFromWhichTheLoopOriginates_case3() {
        var list = SinglyLinkedList.of(0, 1, 2, 3);
        setLoop(list, 3, 3); // 3 -> 3
        assertThat(findLoop(list)).isSameAs(list.nth(3));
    }

    @Test
    void forLinkedListWithALoop_returnsTheNodeFromWhichTheLoopOriginates_case4() {
        var list = SinglyLinkedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        setLoop(list, 9, 0); // 9 -> 0
        assertThat(findLoop(list)).isSameAs(list);
    }

    private static void setLoop(SinglyLinkedList head, int fromIndex, int toIndex) {
        head.nth(fromIndex).next = head.nth(toIndex);
    }
}