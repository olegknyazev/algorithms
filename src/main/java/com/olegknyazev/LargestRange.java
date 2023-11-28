package com.olegknyazev;

import java.util.HashMap;

public class LargestRange {
    // Returns the first and last numbers of the largest natural numbers range present
    // in the given array. For example, for the input [-2, 4, 3, 17, -1, 5] the result
    // would be [3, 5], as the largest range is [3, 4, 5].
    // O(n) runtime, O(n) memory
    public static int[] largestRange(int[] array) {
        if (array.length == 0)
            return new int[0];
        final var chains = buildChains(array);
        int[] longest = null;
        while (!chains.isEmpty()) {
            final var number = chains.keySet().iterator().next();
            final var origin = chains.remove(number);
            chains.remove(origin.value);
            var current = origin;
            while (current.previous != null) {
                current = current.previous;
                chains.remove(current.value);
            }
            final var begin = current.value;
            current = origin;
            while (current.next != null) {
                current = current.next;
                chains.remove(current.value);
            }
            final var end = current.value;
            final var length = end - begin;
            if (longest == null || length > longest[1] - longest[0]) {
                longest = new int[]{begin, end};
            }
        }
        return longest;
    }

    private static HashMap<Integer, Chain> buildChains(int[] array) {
        final var chains = new HashMap<Integer, Chain>();
        for (var number : array) {
            if (chains.containsKey(number))
                continue;
            final var current = new Chain(number);
            final var previous = chains.get(number - 1);
            final var next = chains.get(number + 1);
            if (previous != null)
                previous.append(current);
            if (next != null)
                current.append(next);
            chains.put(number, current);
        }
        return chains;
    }

    private static class Chain {
        public int value;
        public Chain previous;
        public Chain next;

        public Chain(int value) {
            this.value = value;
        }

        public void append(Chain after) {
            this.next = after;
            after.previous = this;
        }
    }
}
