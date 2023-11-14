package com.olegknyazev;

import java.util.ArrayList;
import java.util.List;

public class SpiralGridTraversal {
    // Returns all the array elements in order of spiral traversal, i.e. for array:
    //    1   2   3   4
    //    5   6   7   8
    //    9  10  11  12
    //   13  14  15  16
    // it returns:
    //   1  2  3  4  8  12  16  15  14  13  9  5  6  7  11  10
    // O(n) runtime, O(n) memory
    public static List<Integer> spiralTraverse(int[][] array) {
        final var output = new ArrayList<Integer>();
        final int height = array.length;
        final int width = array[0].length;
        final int diagonal = Math.min(width, height);
        for (int c = 0; c < diagonal / 2 + diagonal % 2; ++c) {
            final int left = c;
            final int right = width - c - 1;
            final int top = c;
            final int bottom = height - c - 1;
            for (int x = left; x <= right; ++x)
                output.add(array[top][x]);
            for (int y = top + 1; y <= bottom - 1; ++y)
                output.add(array[y][right]);
            if (top != bottom)
                for (int x = right; x >= left; --x)
                    output.add(array[bottom][x]);
            if (left != right)
                for (int y = bottom - 1; y >= top + 1; --y)
                    output.add(array[y][left]);
        }
        return output;
    }
}
