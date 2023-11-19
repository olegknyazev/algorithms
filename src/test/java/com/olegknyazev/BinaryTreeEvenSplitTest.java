package com.olegknyazev;

import com.olegknyazev.BinaryTreeEvenSplit.BinaryTree;
import org.junit.jupiter.api.Test;

import static com.olegknyazev.BinaryTreeEvenSplit.sumOfEvenSplit;
import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeEvenSplitTest {
    @Test
    void forSingleNodeTree_returnsZero() {
        assertThat(sumOfEvenSplit(tree(25))).isEqualTo(0);
    }

    @Test
    void forUnsplittableTree_returnsZero() {
        assertThat(sumOfEvenSplit(tree(1, tree(2), tree(1, tree(1), null)))).isEqualTo(0);
    }

    @Test
    void forSplittableTree_returnsSumOfSubtrees() {
        assertThat(sumOfEvenSplit(
                tree(1,
                        tree(3,
                                tree(6, tree(2), null),
                                tree(-5)),
                        tree(-2,
                                tree(5),
                                tree(2)))
        )).isEqualTo(6);
    }

    private static BinaryTree tree(int value) {
        return new BinaryTree(value);
    }

    private static BinaryTree tree(int value, BinaryTree left, BinaryTree right) {
        return new BinaryTree(value, left, right);
    }
}