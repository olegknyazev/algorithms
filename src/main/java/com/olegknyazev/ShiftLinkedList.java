package com.olegknyazev;

import java.util.ArrayList;
import java.util.List;

// The task is to shift (rotate) a linked list in-place by a given offset which may be
// positive (moving elements forward) or negative (moving elements backwards).
public class ShiftLinkedList {
    private ShiftLinkedList() {
        throw new AssertionError();
    }

    // O(n) runtime, O(1) memory
    public static MutableNode shiftInPlace(MutableNode list, int offset) {
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

    public static MutableNode newListOf(int... values) {
        if (values == null || values.length == 0)
            return null;
        MutableNode current = null;
        for (int i = values.length - 1; i >= 0; --i)
            current = new MutableNode(values[i], current);
        return current;
    }

    public static class MutableNode {
        private final int content;
        private MutableNode next;

        public MutableNode(int content, MutableNode next) {
            this.content = content;
            this.next = next;
        }

        public List<Integer> toList() {
            var result = new ArrayList<Integer>();
            var current = this;
            while (current != null) {
                result.add(current.content);
                current = current.next;
            }
            return result;
        }

        public int getLength() {
            var length = 0;
            var current = this;
            while (current != null) {
                ++length;
                current = current.next;
            }
            return length;
        }

        public MutableNode getNth(int offset) {
            var current = this;
            while (offset-- > 0)
                current = current.next;
            return current;
        }
    }
}
