package com.olegknyazev

import java.util.*

object FindTreeNodesAtDistance {
    data class BinaryTree(val value: Int, val left: BinaryTree?, val right: BinaryTree?)

    // Returns the unordered list of values of the nodes that are at the given `distance` from
    // the node having the `target` value. It's supposed that all the values in the `tree`
    // are unique.
    // O(n) runtime, O(d) memory, where d is the tree depth
    fun findNodesAtDistance(tree: BinaryTree, target: Int, distance: Int): List<Int> {
        val path = findPathFromRoot(tree, target)
        val result = mutableListOf<Int>()
        traverseUp(path, path.size - 1, limit = distance, result)
        return result
    }

    private fun findPathFromRoot(tree: BinaryTree, target: Int): Stack<BinaryTree> {
        val result = Stack<BinaryTree>()
        fun visit(node: BinaryTree): BinaryTree? {
            result.add(node)
            if (node.value == target)
                return node
            node.left?.let(::visit)?.let { return it }
            node.right?.let(::visit)?.let { return it }
            result.pop()
            return null
        }
        visit(tree)
        return result
    }

    private fun traverseUp(path: Stack<BinaryTree>, currentPosition: Int, limit: Int, result: MutableList<Int>) {
        val node = path[currentPosition]
        val cameFrom = if (currentPosition < path.size - 1) path[currentPosition + 1] else null
        traverseDownwards(node, cameFrom, limit, result)
        if (limit > 0 && currentPosition > 0)
            traverseUp(path = path, currentPosition = currentPosition - 1, limit = limit - 1, result = result)
        else if (limit == 0)
            result.add(node.value)
    }

    private fun traverseDownwards(
        node: BinaryTree,
        cameFrom: BinaryTree?,
        limit: Int,
        result: MutableList<Int>
    ) {
        if (node.left != null && node.left != cameFrom)
            traverseDown(node.left, limit - 1, result)
        if (node.right != null && node.right != cameFrom)
            traverseDown(node.right, limit - 1, result)
    }

    private fun traverseDown(node: BinaryTree, limit: Int, result: MutableList<Int>) {
        if (limit > 0)
            traverseDownwards(node, cameFrom = null, limit, result)
        else if (limit == 0)
            result.add(node.value)
    }
}