package com.olegknyazev.sorts;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    private MergeSort() {
        throw new AssertionError();
    }

    public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        @SuppressWarnings("unchecked") var from = (T[]) collection.toArray();
        var to = from.clone();
        final var n = collection.size();
        for (var step = 1; step < n; step *= 2) {
            final var chunkSize = step * 2;
            for (var begin = 0; begin < n; begin += chunkSize) {
                final var end = Math.min(begin + chunkSize, n);
                final var mid = Math.min(begin + chunkSize / 2, end);
                var p = begin;
                var p1 = begin;
                var p2 = mid;
                while (p != end)
                    if (p1 < mid && p2 < end) {
                        if (comparator.compare(from[p1], from[p2]) <= 0)
                            to[p++] = from[p1++];
                        else {
                            to[p++] = from[p2++];
                        }
                    } else if (p2 < end)
                        to[p++] = from[p2++];
                    else
                        to[p++] = from[p1++];
            }
            final var temp = from;
            from = to;
            to = temp;
        }
        return Arrays.asList(from);
    }
}
