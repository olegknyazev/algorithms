package com.olegknyazev;

import com.olegknyazev.ListOfDepths.Tree;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.olegknyazev.ListOfDepths.sliceByDepth;
import static org.assertj.core.api.Assertions.assertThat;

class ListOfDepthsTest {
    @Test
    void forEmptyInput_returnsEmptyList() {
        assertThat(sliceByDepth(null)).isEmpty();
    }

    @Test
    void forSingleLevelTree_returnsSingleList() {
        assertThat(sliceByDepth(tree(3))).isEqualTo(List.of(List.of(3)));
    }

    @Test
    void forTwoLevelTree_returnsTwoLists() {
        assertThat(sliceByDepth(tree(3, tree(1), tree(5))))
                .isEqualTo(List.of(List.of(3), List.of(1, 5)));
        assertThat(sliceByDepth(tree(3, tree(1), null)))
                .isEqualTo(List.of(List.of(3), List.of(1)));
        assertThat(sliceByDepth(tree(3, null, tree(5))))
                .isEqualTo(List.of(List.of(3), List.of(5)));
    }

    @Test
    void forThreeLevelTree_returnsThreeLists() {
        assertThat(sliceByDepth(tree(
                3,
                tree(1, tree(0), tree(2)),
                tree(5, tree(4), tree(8)))))
                .isEqualTo(List.of(
                        List.of(3),
                        List.of(1, 5),
                        List.of(0, 2, 4, 8)));

        assertThat(sliceByDepth(tree(
                3,
                tree(1),
                tree(5, tree(4), null))))
                .isEqualTo(List.of(
                        List.of(3),
                        List.of(1, 5),
                        List.of(4)));
    }

    private static Tree tree(int value) {
        return new Tree(value, null, null);
    }

    private static Tree tree(int value, Tree left, Tree right) {
        return new Tree(value, left, right);
    }
}