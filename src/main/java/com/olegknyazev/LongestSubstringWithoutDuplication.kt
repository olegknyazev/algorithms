package com.olegknyazev

object LongestSubstringWithoutDuplication {
    // Returns the longest substring without duplicated characters.
    // O(N) runtime, O(min(N, A)) memory, where A is character alphabet of the input string
    fun longestSubstringWithoutDuplication(string: String): String {
        val closestPosition = mutableMapOf<Char, Int>()
        var start = 0
        var bestStart = 0
        var bestStop = 0
        string.indices.forEach { i ->
            val ch = string[i]
            val previous = closestPosition.put(ch, i)
            if (previous != null && previous >= start) {
                val currentLength = i - start
                if (currentLength > bestStop - bestStart) {
                    bestStart = start
                    bestStop = i
                }
                start = previous + 1
            }
        }
        val currentLength = string.length - start
        if (currentLength > bestStop - bestStart) {
            bestStart = start
            bestStop = string.length
        }
        return string.substring(bestStart, bestStop)
    }
}