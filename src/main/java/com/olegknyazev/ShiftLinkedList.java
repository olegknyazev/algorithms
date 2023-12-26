package com.olegknyazev;

// The task is to shift (rotate) a linked list in-place by a given offset which may be
// positive (moving elements forward) or negative (moving elements backwards).
public class ShiftLinkedList {
    private ShiftLinkedList() {
        throw new AssertionError();
    }

    // O(n) runtime, O(1) memory
    public static MutableSinglyLinkedList shiftInPlace(MutableSinglyLinkedList list, int offset) {
        if (list == null)
            return null;
        var length = list.getLength();
        var boundedOffset = offset % length;
        if (boundedOffset == 0)
            return list;
        if (boundedOffset < 0)
            boundedOffset += length;
        var newTail = list.getNth(length - boundedOffset - 1);
        var oldTail = list.getNth(length - 1);
        var newHead = newTail.next;
        newTail.next = null;
        oldTail.next = list;
        return newHead;
    }
}
