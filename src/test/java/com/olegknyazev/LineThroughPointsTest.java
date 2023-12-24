package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LineThroughPointsTest {
    @Test
    void forEmptyInput_returnsZero() {
        assertThat(lineThroughPoints()).isEqualTo(0);
    }

    @Test
    void forOnePointInput_returnsOne() {
        assertThat(lineThroughPoints(point(2, 4))).isEqualTo(1);
    }

    @Test
    void forRegularInputs_returnsTheMaximumNumberOfPointsContainedInALine() {
        assertThat(lineThroughPoints(
                point(0, 0),
                point(2, 0),
                point(1, 1)
        )).isEqualTo(2);
        assertThat(lineThroughPoints(
                point(1, 1),
                point(2, 2),
                point(3, 3),
                point(0, 4),
                point(-2, 6),
                point(4, 0),
                point(2, 1)
        )).isEqualTo(4);
    }

    private static int lineThroughPoints(LineThroughPoints.Point... points) {
        return LineThroughPoints.lineThroughPoints(points);
    }

    private static LineThroughPoints.Point point(int x, int y) {
        return new LineThroughPoints.Point(x, y);
    }
}