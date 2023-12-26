package com.olegknyazev;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

public class StackOfBoxes {
    // Returns the stack of boxes of maximum height that's possible to build from given `boxes`.
    // Each box in the stack should be strictly smaller in all the dimensions that the previous one.
    // The returned stack is ordered from the tip to the basement.
    // O(n^2) runtime, O(n) memory
    public static List<Box> stackOfMaxHeight(Box[] boxes) {
        if (boxes == null || boxes.length == 0)
            return new ArrayList<>();
        final var sortedBoxes = Arrays.stream(boxes).sorted(comparing(x -> x.height)).toList();
        final var heightsAndReferences = sortedBoxes.stream()
                .map(x -> new HeightAndReference(x.height))
                .toList();
        for (int i = 0; i < boxes.length; ++i) {
            final var basementBox = sortedBoxes.get(i);
            var newBestHeight = 0;
            var newBestNextBoxIndex = -1;
            for (int j = 0; j < i; ++j) {
                final var candidateBox = sortedBoxes.get(j);
                if (candidateBox.strictlySmallerThan(basementBox))
                    if (heightsAndReferences.get(j).maxHeight > newBestHeight) {
                        newBestHeight = heightsAndReferences.get(j).maxHeight;
                        newBestNextBoxIndex = j;
                    }
            }
            heightsAndReferences.get(i).updateNext(newBestNextBoxIndex, newBestHeight);
        }
        final var result = new ArrayDeque<Box>();
        int index = IntStream.range(0, boxes.length).boxed()
                .max(comparing(i -> heightsAndReferences.get(i).maxHeight))
                .orElseThrow();
        do {
            result.addFirst(sortedBoxes.get(index));
            index = heightsAndReferences.get(index).nextBoxIndex;
        } while (index != -1);
        return result.stream().toList();
    }

    public record Box(int width, int height, int depth) {
        public boolean strictlySmallerThan(Box other) {
            return width < other.width && height < other.height && depth < other.depth;
        }
    }

    private static class HeightAndReference {
        public int maxHeight;
        public int nextBoxIndex;

        public HeightAndReference(int height) {
            maxHeight = height;
            nextBoxIndex = -1;
        }

        public void updateNext(int nextBoxIndex, int heightDelta) {
            this.nextBoxIndex = nextBoxIndex;
            this.maxHeight = this.maxHeight + heightDelta;
        }
    }
}
