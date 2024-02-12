package com.olegknyazev

import com.olegknyazev.FindTreeNodesAtDistance.BinaryTree
import com.olegknyazev.FindTreeNodesAtDistance.findNodesAtDistance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FindTreeNodesAtDistanceTest {
    @Test
    fun `for k = 0 returns the target itself`() {
        assertThat(findNodesAtDistance(tree(42), target = 42, distance = 0)).containsExactly(42)
        assertThat(findNodesAtDistance(tree(42, tree(17)), target = 17, distance = 0)).containsExactly(17)
    }

    @Test
    fun `for k = 2 returns all the nodes at the distance`() {
        assertThat(
            findNodesAtDistance(
                tree(
                    1,
                    tree(
                        2,
                        tree(3, tree(8), tree(9)),
                        tree(4, tree(10), tree(11))
                    ),
                    tree(5, tree(6), tree(7))
                ),
                target = 4,
                distance = 2
            )
        ).containsExactly(3, 1)
    }

    private fun tree(value: Int, left: BinaryTree? = null, right: BinaryTree? = null) = BinaryTree(value, left, right)
}