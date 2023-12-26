package com.olegknyazev;

public class ReverseLinkedList {
    // Reverses the list in place and return the head of the new, reversed list.
    // O(n) runtime, O(1) memory
    public static MutableSinglyLinkedList reverseInPlace(MutableSinglyLinkedList head) {
        if (head == null || head.next == null)
            return head;
        var current = head;
        var next = head.next;
        head.next = null;
        do {
            final var afterNext = next.next;
            next.next = current;
            current = next;
            next = afterNext;
        } while (next != null);
        return current;
    }
}
