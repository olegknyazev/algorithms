package com.olegknyazev

object LevenshteinDistance {
    fun levenshteinDistance(a: String, b: String): Int =
        recursiveMemoizedLevenshteinDistance(a, 0, b, 0, newMemo(a, b))

    // O(3 ^ max(N, M)) runtime, O(max(N, M)) memory
    // where N, M - lengths of the strings
    private fun recursiveLevenshteinDistance(a: String, aStart: Int, b: String, bStart: Int): Int =
        when {
            aStart >= a.length -> b.length - bStart
            bStart >= b.length -> a.length - aStart
            a[aStart] == b[bStart] -> recursiveLevenshteinDistance(a, aStart + 1, b, bStart + 1)
            else -> 1 + minOf(
                recursiveLevenshteinDistance(a, aStart + 1, b, bStart),
                recursiveLevenshteinDistance(a, aStart, b, bStart + 1),
                recursiveLevenshteinDistance(a, aStart + 1, b, bStart + 1),
            )
        }

    // O(N * M) runtime, O(N * M) memory
    private fun recursiveMemoizedLevenshteinDistance(
        a: String,
        aStart: Int,
        b: String,
        bStart: Int,
        memo: Array<IntArray>
    ): Int {
        val precomputed = memo[aStart][bStart]
        if (precomputed != -1)
            return precomputed
        val distance = when {
            aStart >= a.length -> b.length - bStart
            bStart >= b.length -> a.length - aStart
            a[aStart] == b[bStart] -> recursiveMemoizedLevenshteinDistance(a, aStart + 1, b, bStart + 1, memo)
            else -> 1 + minOf(
                recursiveMemoizedLevenshteinDistance(a, aStart + 1, b, bStart, memo),
                recursiveMemoizedLevenshteinDistance(a, aStart, b, bStart + 1, memo),
                recursiveMemoizedLevenshteinDistance(a, aStart + 1, b, bStart + 1, memo),
            )
        }
        memo[aStart][bStart] = distance
        return distance
    }

    private fun newMemo(a: String, b: String) =
        Array(a.length + 1) { IntArray(b.length + 1) { -1 } }
}