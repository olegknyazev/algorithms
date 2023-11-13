package com.olegknyazev;

public class SinglyLinkedListLoop {
    // Returns the origin of the loop in the passed list, if there's any.
    // Otherwise, returns null.
    // O(n) runtime, O(1) memory
    public static SinglyLinkedList findLoop(SinglyLinkedList head) {
        if (head == null)
            return null;
        var slow = new TraverseState(head);
        var fast = new TraverseState(head);
        while (true) {
            slow.advance();
            if (!fast.advance())
                break;
            if (!fast.advance())
                break;
            if (slow.current == fast.current) {
                var origin = new TraverseState(head);
                while (origin.current != slow.current) {
                    origin.advance();
                    slow.advance();
                }
                return origin.current;
            }
        }
        return null;
    }

    static class TraverseState {
        public SinglyLinkedList current;
        public int steps;

        public TraverseState(SinglyLinkedList start) {
            this.current = start;
        }

        boolean advance() {
            ++steps;
            current = current.next;
            return current != null;
        }
    }

    public static class SinglyLinkedList {
        int value;
        SinglyLinkedList next = null;

        public SinglyLinkedList(int value) {
            this.value = value;
        }

        public SinglyLinkedList nth(int n) {
            var result = this;
            for (int i = 0; i < n; ++i)
                result = result.next;
            return result;
        }

        public static SinglyLinkedList of(int... values) {
            SinglyLinkedList head = null;
            SinglyLinkedList curr = null;
            for (var x : values) {
                var next = new SinglyLinkedList(x);
                if (head == null)
                    head = next;
                if (curr != null)
                    curr.next = next;
                curr = next;
            }
            return head;
        }
    }
}
