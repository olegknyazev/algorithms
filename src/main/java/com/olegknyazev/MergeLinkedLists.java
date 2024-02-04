package com.olegknyazev;

public class MergeLinkedLists {
    // Merges two ordered linked lists in place to make a single ordered linked list.
    // O(N + M) runtime, O(1) memory
    public static MutableSinglyLinkedList mergeLists(MutableSinglyLinkedList first, MutableSinglyLinkedList second) {
        if (first == null) return second;
        if (second == null) return first;
        MutableSinglyLinkedList a = first;
        MutableSinglyLinkedList b = second;
        MutableSinglyLinkedList newHead = null;
        MutableSinglyLinkedList current = null;
        while (a != null && b != null) {
            if (a.content < b.content) {
                if (current != null)
                    current.next = a;
                current = a;
                a = a.next;
                if (a == null)
                    current.next = b;
            } else {
                if (current != null)
                    current.next = b;
                current = b;
                b = b.next;
                if (b == null)
                    current.next = a;
            }
            if (newHead == null)
                newHead = current;
        }
        return newHead;
    }

    private MergeLinkedLists() {
        throw new AssertionError();
    }
}
