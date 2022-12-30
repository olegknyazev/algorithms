package com.olegknyazev;

import java.util.Arrays;

// The task is to compute the maximum profit that could be made from stock trade within
// a fixed number transactions. The following restrictions are applied:
//  - Only one buy or sell could be made at each single day.
//  - It's not possible to buy stocks if we already have any.
//  - All the stocks are sold at the same moment.
public class MaxProfitWithKTransactions {
    // O(K * n) runtime, O(K * n) memory
    static public int maxProfit(int[] prices, int maxTransactions) {
        if (prices == null || prices.length < 2)
            return 0;
        if (maxTransactions == 0)
            return 0;
        var maxProfitUpToDay = new int[maxTransactions + 1][prices.length];
        for (int k = 1; k <= maxTransactions; ++k) {
            int[] prevMaxProfitUpToDay = maxProfitUpToDay[k - 1];
            int[] currMaxProfitUpToDay = maxProfitUpToDay[k];
            var start = (k - 1) * 2;
            var end = start + 1;
            var maxProfit = 0;
            while (end < prices.length) {
                var previousSplitIncrease = k > 1 ? prevMaxProfitUpToDay[end - 1] - prevMaxProfitUpToDay[start - 1] : 0;
                var thisSplitIncrease = prices[start] - prices[end - 1];
                if (thisSplitIncrease + previousSplitIncrease >= 0) {
                    start = end - 1;
                }
                int profit = Math.max(prices[end] - prices[start], 0) + (k > 1 ? prevMaxProfitUpToDay[start - 1] : 0);
                maxProfit = Math.max(profit, maxProfit);
                currMaxProfitUpToDay[end] = maxProfit;
                ++end;
            }
        }
        return Arrays.stream(maxProfitUpToDay).flatMapToInt(Arrays::stream).max().orElse(0);
    }
}
