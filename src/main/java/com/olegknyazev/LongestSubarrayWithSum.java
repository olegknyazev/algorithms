package com.olegknyazev;

public class LongestSubarrayWithSum {
    // Return the two-element array containing the start and end indices of the longest
    // subarray in the `array`, whose sum is equal to `targetSum`. Returns an empty array
    // if there's no subarray in `array` whose sum is equal to `targetSum`.
    // O(n) runtime, O(1) memory
    public static int[] longestSubarrayWithSum(int[] array, int targetSum) {
        if (array.length == 0)
            return new int[]{};
        int[] bestRange = new int[]{};
        var start = 0;
        var end = 0;
        var sum = array[end];
        while (true) {
            if (sum == targetSum) {
                var bestLength = bestRange.length == 0 ? -1 : (bestRange[1] - bestRange[0]);
                var curLength = end - start;
                if (curLength > bestLength)
                    bestRange = new int[]{start, end};
            }
            if (sum <= targetSum) {
                ++end;
                if (end == array.length)
                    break;
                sum += array[end];
            } else {
                sum -= array[start];
                ++start;
            }
        }
        return bestRange;
    }
}
