package com.olegknyazev;

import com.olegknyazev.MaxPathSumInBinaryTree.BinaryTree;
import org.junit.jupiter.api.Test;

import static com.olegknyazev.MaxPathSumInBinaryTree.maxPathSum;
import static org.assertj.core.api.Assertions.assertThat;

class MaxPathSumInBinaryTreeTest {
    @Test
    void forSingleNodeTree_returnsTheNodeValue() {
        assertThat(maxPathSum(tree(15))).isEqualTo(15);
    }

    @Test
    void forLargerTree_returnsTheMaxSumPath() {
        assertThat(maxPathSum(
                tree(1,
                        tree(2, tree(4), tree(5)),
                        tree(3, tree(6), tree(7))))
        ).isEqualTo(18);
    }

    @Test
    void forLargerTree_returnsTheMaxSumPath_case2() {
        assertThat(maxPathSum(
                tree(1,
                        tree(-10,
                                tree(30, tree(5), tree(1)),
                                tree(45, tree(3), tree(-3))),
                        tree(-5,
                                tree(-20, tree(100), tree(2)),
                                tree(-21, tree(100), tree(1))))
        )).isEqualTo(154);
    }

    @Test
    void forTreeWhereTheMaxDoesNotIncludeTheFullPath_stillReturnsTheCorrectResult() {
        assertThat(maxPathSum(
                tree(1,
                        tree(-150,
                                tree(30,
                                        tree(5, tree(100), tree(100)),
                                        tree(1, tree(5), tree(10))),
                                tree(75,
                                        tree(3, tree(150), tree(-8)),
                                        tree(-3))),
                        tree(-5,
                                tree(-20, tree(100), tree(2)),
                                tree(-21, tree(100), tree(1))))
        )).isEqualTo(228);
    }

    private static BinaryTree tree(int value) {
        return new BinaryTree(value);
    }

    private static BinaryTree tree(int value, BinaryTree left, BinaryTree right) {
        return new BinaryTree(value, left, right);
    }
}