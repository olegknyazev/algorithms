package com.olegknyazev;

public class SingleCycleCheck {
    // In array each element describes a jump: it's the distance from the current index
    // to the next index. +1 means one step forward, -1 means one step backward. A loop
    // is a series of jumps starting from an arbitrary index that ends at the same index.
    // The function returns true if the given array contains exactly one loop, covering
    // all the elements.
    // O(n) runtime, O(1) memory
    public static boolean hasSingleCycle(int[] array) {
        if (array.length == 0)
            return false;
        if (array.length == 1)
            return true;
        var jumpsCount = 0;
        var jumpsSum = 0;
        var current = 0;
        while (jumpsCount < array.length) {
            final var next = jump(array, current);
            if (next == current)
                return false;
            final var distance = next - current;
            ++jumpsCount;
            jumpsSum += distance;
            current = next;
            if (current == 0)
                return jumpsSum == 0 && jumpsCount == array.length;
        }
        return false;
    }

    private static int jump(int[] array, int currentIndex) {
        final var delta = array[currentIndex];
        final var newIndex = (currentIndex + delta) % array.length;
        return newIndex >= 0 ? newIndex : newIndex + array.length;
    }
}
