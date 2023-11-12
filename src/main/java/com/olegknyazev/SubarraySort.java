package com.olegknyazev;

public class SubarraySort {
    // Returns the indices of shortest possible unsorted strip in the array, sorting
    // which will make the entire array sorted.
    // Returns NO_RANGE if the array is already sorted.
    // O(n) runtime, O(1) memory
    public static Range unsortedSubarray(int[] array) {
        var minUnsorted = Integer.MAX_VALUE;
        var maxUnsorted = Integer.MIN_VALUE;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[i - 1]) {
                if (minUnsorted > array[i])
                    minUnsorted = array[i];
                if (minUnsorted > array[i - 1])
                    minUnsorted = array[i - 1];
                if (maxUnsorted < array[i])
                    maxUnsorted = array[i];
                if (maxUnsorted < array[i - 1])
                    maxUnsorted = array[i - 1];
            }
        }
        var start = -1;
        var end = -1;
        for (int i = 0; i < array.length; ++i) {
            if (start == -1 && array[i] > minUnsorted)
                start = i;
            if (array[i] < maxUnsorted)
                end = i;
        }
        return new Range(start, end);
    }

    public record Range(int startIndex, int endIndex) {
    }

    public static Range NO_RANGE = new Range(-1, -1);
}
