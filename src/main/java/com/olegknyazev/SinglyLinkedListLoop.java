package com.olegknyazev;

public class SinglyLinkedListLoop {
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
                var anchor = slow.current;
                var probe = anchor;
                var minDistance = Integer.MAX_VALUE;
                do {
                    minDistance = Math.min(minDistance, distance(head, probe));
                    probe = probe.next;
                } while (probe != anchor);
                return head.nth(minDistance);
            }
        }
        return null;
    }

    private static int distance(SinglyLinkedList head, SinglyLinkedList needle) {
        var traverse = new TraverseState(head);
        while (traverse.current != needle)
            traverse.advance();
        return traverse.steps;
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
