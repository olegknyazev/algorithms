package com.olegknyazev

class LRUCache(private val capacity: Int) {
    init {
        require(capacity > 0)
    }

    private val cache = mutableMapOf<Int, CacheNode>()
    private var mostRecentNode: CacheNode? = null
    private var leastRecentNode: CacheNode? = null

    fun get(key: Int): Int = cache[key]?.also(::touch)?.value ?: -1

    fun put(key: Int, value: Int) {
        val node = cache.compute(key) { _, node ->
            node?.apply { this.value = value } ?: CacheNode(key, value)
        }!!
        touch(node)
        evictIfExceedsCapacity()
    }

    private fun touch(node: CacheNode) {
        if (mostRecentNode == node)
            return
        if (leastRecentNode == node)
            leastRecentNode = node.next

        node.next?.prev = node.prev
        node.prev?.next = node.next

        node.prev = mostRecentNode
        mostRecentNode?.next = node
        mostRecentNode = node
        if (leastRecentNode == null)
            leastRecentNode = node
    }

    private fun evictIfExceedsCapacity() {
        if (cache.size > capacity) {
            cache.remove(leastRecentNode!!.key)
            leastRecentNode = leastRecentNode!!.next
            leastRecentNode!!.prev = null
        }
    }

    private class CacheNode(
        val key: Int,
        var value: Int,
        var prev: CacheNode? = null,
        var next: CacheNode? = null,
    )
}