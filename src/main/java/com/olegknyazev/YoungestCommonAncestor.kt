package com.olegknyazev

object YoungestCommonAncestor {
    data class AncestralTree(val name: Char, val ancestor: AncestralTree? = null)

    // O(D) runtime, O(1) memory
    // where D is the depth of the tree
    fun getYoungestCommonAncestor(
        topAncestor: AncestralTree,
        descendantOne: AncestralTree,
        descendantTwo: AncestralTree
    ): AncestralTree? {
        val distanceOne = distance(descendantOne, topAncestor)
        val distanceTwo = distance(descendantTwo, topAncestor)
        var one = nthAncestor(descendantOne, (distanceOne - distanceTwo).coerceAtLeast(0))
        var two = nthAncestor(descendantTwo, (distanceTwo - distanceOne).coerceAtLeast(0))
        while (one != two) {
            one = one?.ancestor
            two = two?.ancestor
        }
        return one
    }

    private fun distance(descendant: AncestralTree, ancestor: AncestralTree): Int {
        var distance = 0
        var current: AncestralTree? = descendant
        while (current != null && current != ancestor) {
            ++distance
            current = current.ancestor
        }
        return distance
    }

    private fun nthAncestor(node: AncestralTree, n: Int): AncestralTree? {
        var current: AncestralTree? = node
        repeat(n) {
            current = current?.ancestor
        }
        return current
    }
}