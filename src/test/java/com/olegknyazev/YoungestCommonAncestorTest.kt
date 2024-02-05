package com.olegknyazev

import com.olegknyazev.YoungestCommonAncestor.AncestralTree
import com.olegknyazev.YoungestCommonAncestor.getYoungestCommonAncestor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class YoungestCommonAncestorTest {
    @Test
    fun `for single node tree return the node`() {
        val root = tree('A')
        assertThat(getYoungestCommonAncestor(root, root, root)).isSameAs(root)
    }

    @Test
    fun `for regular case returns the common youngest ancestor`() {
        val a = tree('A')
        val b = tree('B', a)
        val c = tree('C', a)
        val d = tree('D', b)
        val e = tree('E', b)
        val f = tree('F', c)
        val g = tree('G', c)
        val h = tree('H', d)
        val i = tree('I', d)
        assertThat(getYoungestCommonAncestor(a, e, i)).isSameAs(b)
    }

    @Test
    fun `for case when one of the nodes is the other's ancestor returns the ancestor`() {
        val a = tree('A')
        val b = tree('B', a)
        val c = tree('C', b)
        val d = tree('D', c)
        assertThat(getYoungestCommonAncestor(a, b, d)).isSameAs(b)
    }

    private fun tree(name: Char, ancestor: AncestralTree? = null) = AncestralTree(name, ancestor)
}