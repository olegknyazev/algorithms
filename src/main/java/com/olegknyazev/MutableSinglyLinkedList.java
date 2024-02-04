package com.olegknyazev;

import java.util.ArrayList;
import java.util.List;

public class MutableSinglyLinkedList {
    final int content;
    MutableSinglyLinkedList next;

    public MutableSinglyLinkedList(int content, MutableSinglyLinkedList next) {
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

    public MutableSinglyLinkedList getNth(int offset) {
        var current = this;
        while (offset-- > 0)
            current = current.next;
        return current;
    }

    public static MutableSinglyLinkedList of(int... values) {
        if (values == null || values.length == 0)
            return null;
        MutableSinglyLinkedList current = null;
        for (int i = values.length - 1; i >= 0; --i)
            current = new MutableSinglyLinkedList(values[i], current);
        return current;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MutableSinglyLinkedList other))
            return false;
        return toList().equals(other.toList());
    }
}
