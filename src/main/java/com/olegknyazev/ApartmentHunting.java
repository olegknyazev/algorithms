package com.olegknyazev;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ApartmentHunting {
    // The function takes a list of blocks that are located along a street and a list of
    // required buildings. Every block may contain buildings of several types. Whether a
    // block contain a specific building or not is determined by map entry.
    // Returns index of a block, for which the farthest distance to any of the required
    // buildings is smaller.
    // O(N * M) runtime, O(N * M) memory,
    // where N is the number of blocks and M is the number of requirements.
    public static int findBestBlock(List<Map<String, Boolean>> blocks, String[] requirements) {
        int[][] distances = new int[requirements.length][blocks.size()];
        for (int r = 0; r < requirements.length; ++r) {
            Arrays.fill(distances[r], Integer.MAX_VALUE);
            for (int i = 0; i < blocks.size(); ++i)
                if (blocks.get(i).getOrDefault(requirements[r], false))
                    distances[r][i] = 0;
                else if (i > 0)
                    distances[r][i] = distances[r][i - 1] != Integer.MAX_VALUE ? distances[r][i - 1] + 1 : distances[r][i - 1];
            for (int i = blocks.size() - 1; i >= 0; --i)
                if (blocks.get(i).getOrDefault(requirements[r], false))
                    distances[r][i] = 0;
                else if (i < blocks.size() - 1)
                    distances[r][i] = Math.min(distances[r][i], distances[r][i + 1] + 1);
        }
        var bestSum = Integer.MAX_VALUE;
        var bestIndex = -1;
        for (int i = 0; i < blocks.size(); ++i) {
            final var ii = i;
            var sum = IntStream.range(0, requirements.length).map(r -> distances[r][ii]).max().orElse(Integer.MAX_VALUE);
            if (sum < bestSum) {
                bestSum = sum;
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}
