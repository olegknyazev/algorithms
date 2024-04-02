package com.olegknyazev

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LRUCacheTest {
    @Test
    fun `zero-capacity cache cannot be constructed`() {
        assertThrows<IllegalArgumentException> {
            LRUCache(0)
        }
    }

    @Test
    fun `returns -1 for non-existing keys`() {
        with(LRUCache(10)) {
            assertThat(get(1)).isEqualTo(-1)
            assertThat(get(2)).isEqualTo(-1)
        }
    }

    @Test
    fun `returns the value associated with the key for the previously added keys`() {
        with(LRUCache(10)) {
            put(1, 10)
            put(2, 20)
            assertThat(get(1)).isEqualTo(10)
            assertThat(get(2)).isEqualTo(20)
        }
    }

    @Test
    fun `if the next put exceeds capacity, the least recently used key should be evicted`() {
        with(LRUCache(2)) {
            put(1, 1)
            put(2, 2)
            put(3, 3)
            assertThat(get(1)).isEqualTo(-1)
            assertThat(get(2)).isEqualTo(2)
            assertThat(get(3)).isEqualTo(3)
        }
    }

    @Test
    fun `refresh of the least used key is handled correctly`() {
        with(LRUCache(2)) {
            put(1, 1)
            put(2, 2)
            get(1)
            put(3, 3)
            assertThat(get(2)).isEqualTo(-1)
            assertThat(get(1)).isEqualTo(1)
            assertThat(get(3)).isEqualTo(3)
        }
    }

    @Test
    fun `refresh of the key in the middle of use history is handled correctly`() {
        with(LRUCache(3)) {
            put(1, 1)
            put(2, 2)
            put(3, 3)
            get(2)
            put(4, 4)
            assertThat(get(1)).isEqualTo(-1)
            assertThat(get(4)).isEqualTo(4)
            assertThat(get(3)).isEqualTo(3)
            assertThat(get(2)).isEqualTo(2)
        }
    }

    @Test
    fun `refresh of the most recently used key is handled correctly`() {
        with(LRUCache(2)) {
            put(1, 1)
            put(2, 2)
            get(2)
            put(3, 3)
            assertThat(get(1)).isEqualTo(-1)
            assertThat(get(3)).isEqualTo(3)
            assertThat(get(2)).isEqualTo(2)
        }
    }

    @Test
    fun `puts within the capacity don't evict`() {
        with(LRUCache(3)) {
            repeat(3) {
                put(1, it * 3 + 1)
                put(2, it * 3 + 2)
                put(3, it * 3 + 3)
            }
            assertThat(get(1)).isEqualTo(7)
            assertThat(get(2)).isEqualTo(8)
            assertThat(get(3)).isEqualTo(9)
        }
    }
}