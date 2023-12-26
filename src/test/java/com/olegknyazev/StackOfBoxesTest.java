package com.olegknyazev;

import com.olegknyazev.StackOfBoxes.Box;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StackOfBoxesTest {
    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertThat(stackOfMaxHeight()).isEmpty();
    }

    @Test
    void forSingleInputBox_returnsTheInput() {
        assertThat(stackOfMaxHeight(box(1, 2, 3))).containsExactly(box(1, 2, 3));
    }

    @Test
    void forRegularInput_returnsTheTallestStack() {
        assertThat(stackOfMaxHeight(
                box(2, 2, 1),
                box(3, 3, 2),
                box(2, 8, 2),
                box(2, 4, 3),
                box(1, 1, 3),
                box(4, 5, 4)
        )).containsExactly(
                box(2, 2, 1),
                box(3, 3, 2),
                box(4, 5, 4)
        );
    }

    private static List<Box> stackOfMaxHeight(Box... boxes) {
        return StackOfBoxes.stackOfMaxHeight(boxes);
    }

    private static Box box(int width, int height, int depth) {
        return new Box(width, height, depth);
    }
}