package com.olegknyazev;

public class MinNumberOfJumps {
    // Each number in the input arrays represents the maximum length of the jump that
    // can be made from this index. The function returns the minimum number of jumps
    // that should be done to traverse through the entire array.
    // For example, the array [2, 3, 1, 3, 2, 1, 2] can be traversed in 3 jumps:
    //   2 -> 3 -> 3 -> 2
    // O(n) runtime, O(1) memory
    public static int minNumberOfJumps(int[] array) {
        if (array.length == 0)
            return 0;
        var start = 0;
        var jumps = 0;
        var lease = array[0];
        var newBestLease = lease;
        for (int i = 0; i < array.length; ++i) {
            var newLease = i + array[i];
            newBestLease = Math.max(newLease, newBestLease);
            if (i == lease) {
                start = lease;
                lease = newBestLease;
                ++jumps;
            }
        }
        if (start < array.length - 1)
            ++jumps;
        return jumps;
    }
}
