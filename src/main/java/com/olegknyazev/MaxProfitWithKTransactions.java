package com.olegknyazev;

// The task is to compute the maximum profit that could be made from stock trade within
// a fixed number transactions. The following restrictions are applied:
//  - Only one buy or sell could be made at each single day.
//  - It's not possible to buy stocks if we already have any.
//  - All the stocks are sold at the same moment.
public class MaxProfitWithKTransactions {
    // O(n!) runtime, O(1) memory :(
    static public int maxProfit(int[] prices, int maxTransactions) {
        if (prices == null || prices.length < 2)
            return 0;
        if (maxTransactions == 0)
            return 0;
        int best = maxProfitOfBestTransactionInRange(prices, 0, prices.length);
        var maxSubdivisions = Math.min(maxTransactions, prices.length / 2);
        for (int subdivisions = 1; subdivisions < maxSubdivisions; ++subdivisions) {
            var indices = new int[subdivisions];
            for (int j = 0; j < subdivisions; ++j)
                indices[j] = 2 + j * 2;
            do {
                var profit = maxProfitOfSplit(prices, indices);
                if (profit > best)
                    best = profit;
            } while (nextPermutationInPlace(indices, prices.length));
        }
        return best;
    }

    static boolean nextPermutationInPlace(int[] indices, int dataLength) {
        var current = indices.length - 1;
        while (true) {
            var last = indices[current];
            var maxIndex = dataLength - (indices.length - current) * 2;
            if (last < maxIndex) {
                var base = ++indices[current];
                for (int j = current + 1; j < indices.length; ++j)
                    indices[j] = base + (j - current) * 2;
                return true;
            } else if (current > 0) {
                --current;
            } else {
                return false;
            }
        }
    }

    static int maxProfitOfSplit(int[] prices, int[] splitIndices) {
        var sum = 0;
        var prev = 0;
        for (int i = 0; i < splitIndices.length + 1; ++i) {
            var next = i < splitIndices.length ? splitIndices[i] : prices.length;
            sum += maxProfitOfBestTransactionInRange(prices, prev, next);
            prev = next;
        }
        return sum;
    }

    static int maxProfitOfBestTransactionInRange(int[] prices, int startInclusive, int endExclusive) {
        var left = startInclusive;
        var right = endExclusive - 1;
        var max = 0;
        while (left < right) {
            var current = prices[right] - prices[left];
            if (current > max)
                max = current;
            var leftDelta = prices[left] - prices[left + 1];
            var rightDelta = prices[right - 1] - prices[right];
            if (leftDelta > rightDelta)
                ++left;
            else
                --right;
        }
        return max;
    }
}
