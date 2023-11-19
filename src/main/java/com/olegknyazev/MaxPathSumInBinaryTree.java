package com.olegknyazev;

import java.util.Arrays;

public class MaxPathSumInBinaryTree {
    // Returns the maximum possible sum of a path in the given tree.
    // The path is a chain of nodes, where each of them is connected to no more than two others.
    // O(N) runtime, O(log(N)) memory,
    // where N is the number of nodes in tree
    public static int maxPathSum(BinaryTree tree) {
        final var options = traverse(tree);
        return Math.max(options.withRoot, options.withoutRoot);
    }

    private static Options traverse(BinaryTree tree) {
        if (tree == null)
            return Options.ZERO;
        final var leftOptions = traverse(tree.left);
        final var rightOptions = traverse(tree.right);
        final var withoutRoot = maxOf(
                tree.value + leftOptions.withRoot + rightOptions.withRoot,
                tree.value + leftOptions.withRoot,
                tree.value + rightOptions.withRoot,
                leftOptions.withoutRoot,
                rightOptions.withoutRoot);
        final var withRoot = maxOf(
                tree.value + leftOptions.withRoot,
                tree.value + rightOptions.withRoot);
        return new Options(withoutRoot, withRoot);
    }

    private record Options(int withoutRoot, int withRoot) {
        static Options ZERO = new Options(Integer.MIN_VALUE, 0);
    }

    private static int maxOf(int... values) {
        return Arrays.stream(values).max().orElseThrow();
    }

    public static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

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
