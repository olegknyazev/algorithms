package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.SpiralGridTraversal.spiralTraverse;
import static org.assertj.core.api.Assertions.assertThat;

class SpiralGridTraversalTest {
    @Test
    void forSingleElementArray_returnsJustIt() {
        assertThat(spiralTraverse(grid(1, 1).row(1).build())).containsExactly(1);
    }

    @Test
    void forSquareArray_returnsSpiralTraversal() {
        assertThat(spiralTraverse(
                grid(4, 4)
                        .row(1, 2, 3, 4)
                        .row(5, 6, 7, 8)
                        .row(9, 10, 11, 12)
                        .row(13, 14, 15, 16)
                        .build()
        )).containsExactly(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10);
    }

    @Test
    void forVerticalArray_returnsSpiralTraversal() {
        assertThat(spiralTraverse(
                grid(3, 4)
                        .row(1, 2, 3)
                        .row(4, 5, 6)
                        .row(7, 8, 9)
                        .row(10, 11, 12)
                        .build()
        )).containsExactly(1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8);
    }

    @Test
    void forHorizontalArray_returnsSpiralTraversal() {
        assertThat(spiralTraverse(
                grid(4, 3)
                        .row(1, 2, 3, 4)
                        .row(5, 6, 7, 8)
                        .row(9, 10, 11, 12)
                        .build()
        )).containsExactly(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
    }

    private static GridBuilder grid(int width, int height) {
        return new GridBuilder(width, height);
    }

    private static class GridBuilder {
        private final int width;
        private final int[][] output;
        private int y;

        public GridBuilder(int width, int height) {
            this.width = width;
            this.output = new int[height][width];
        }

        public GridBuilder row(int... items) {
            assert items.length == width;
            output[y++] = items;
            return this;
        }

        public int[][] build() {
            return output;
        }
    }
}