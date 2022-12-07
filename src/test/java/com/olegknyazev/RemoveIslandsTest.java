package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.RemoveIslands.removeIslands;
import static org.assertj.core.api.Assertions.assertThat;

class RemoveIslandsTest {
    private static final boolean T = true;
    private static final boolean F = false;

    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertIslandsRemovalIs(null, null);
        assertIslandsRemovalIs(new boolean[0][0], new boolean[0][0]);
        assertIslandsRemovalIs(new boolean[2][3], new boolean[2][3]);
    }

    @Test
    void forInputWithIslands_returnsBitmapWithIslandsRemoved() {
        assertIslandsRemovalIs(
                new boolean[][]{
                        {T, T, F, F, T, F},
                        {T, F, F, T, F, T},
                        {T, T, T, F, F, T},
                        {F, F, F, F, T, F},
                        {F, T, T, T, T, F},
                        {T, F, F, F, F, F},
                },
                new boolean[][]{
                        {T, T, F, F, T, F},
                        {T, F, F, F, F, T},
                        {T, T, T, F, F, T},
                        {F, F, F, F, F, F},
                        {F, F, F, F, F, F},
                        {T, F, F, F, F, F},
                }
        );
    }

    private static void assertIslandsRemovalIs(boolean[][] input, boolean[][] output) {
        assertThat(removeIslands(input)).isDeepEqualTo(output);
    }
}