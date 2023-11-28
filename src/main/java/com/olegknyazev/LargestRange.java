package com.olegknyazev;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;

public class LargestRange {
    // Returns the first and last numbers of the largest natural numbers range present
    // in the given array. For example, for the input [-2, 4, 3, 17, -1, 5] the result
    // would be [3, 5], as the largest range is [3, 4, 5].
    // O(n) runtime, O(n) memory
    public static int[] largestRange(int[] array) {
        if (array.length == 0)
            return new int[0];
        final var remaining = Arrays.stream(array).boxed().collect(toSet());
        int[] longest = null;
        while (!remaining.isEmpty()) {
            final int number = remaining.iterator().next();
            if (remaining.remove(number)) {
                var start = number;
                while (remaining.remove(start - 1))
                    --start;
                var end = number;
                while (remaining.remove(end + 1))
                    ++end;
                final var length = end - start;
                if (longest == null || length > longest[1] - longest[0])
                    longest = new int[]{start, end};
            }
        }
        return longest;
    }
}
