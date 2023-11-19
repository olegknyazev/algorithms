package com.olegknyazev;

import java.util.IdentityHashMap;

public class BinaryTreeEvenSplit {
    // Returns the sum of subtrees produced after removing a single node in case if
    // sums of both subtrees are equal. If there's no such split, returns 0.
    // O(n) runtime, O(n) memory
    public static int sumOfEvenSplit(BinaryTree tree) {
        final var subtreeSum = new IdentityHashMap<BinaryTree, Integer>();
        calculateSubtreeSum(tree, subtreeSum);
        final var totalSub = subtreeSum.get(tree);
        for (final var option : subtreeSum.values())
            if (option * 2 == totalSub)
                return option;
        return 0;
    }

    private static void calculateSubtreeSum(BinaryTree tree, IdentityHashMap<BinaryTree, Integer> output) {
        if (tree == null)
            return;
        calculateSubtreeSum(tree.left, output);
        calculateSubtreeSum(tree.right, output);
        output.put(tree, output.getOrDefault(tree.left, 0) + output.getOrDefault(tree.right, 0) + tree.value);
    }

    public static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }

        public BinaryTree(int value, BinaryTree left, BinaryTree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
