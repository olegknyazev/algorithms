package com.olegknyazev.sorts;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
    private QuickSort() {
        throw new AssertionError();
    }

    public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        @SuppressWarnings("unchecked") var result = (T[]) collection.toArray();
        sortSubrange(result, 0, result.length - 1, comparator);
        return Arrays.asList(result);
    }

    private static <T> void sortSubrange(T[] data, int start, int end, Comparator<T> comparator) {
        if (start >= end)
            return;
        final var pivot = data[start];
        var l = start + 1;
        var r = end;
        while (true) {
            while (comparator.compare(data[l], pivot) <= 0 && l < end) ++l;
            while (comparator.compare(data[r], pivot) > 0) --r;
            if (l >= r) break;
            swap(data, l, r);
        }
        swap(data, start, r);
        sortSubrange(data, start, r - 1, comparator);
        sortSubrange(data, l, end, comparator);
    }

    private static <T> void swap(T[] data, int firstIndex, int secondIndex) {
        T temp = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = temp;
    }
}
