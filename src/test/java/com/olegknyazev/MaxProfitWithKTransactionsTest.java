package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.MaxProfitWithKTransactions.maxProfit;
import static org.assertj.core.api.Assertions.assertThat;

class MaxProfitWithKTransactionsTest {
    @Test
    void ifPricesContainLessThanTwoValues_shouldReturnZero() {
        assertMaxProfitIs(null, 1, 0);
        assertMaxProfitIs(new int[]{1}, 1, 0);
    }

    @Test
    void ifMaxTransactionsIsZero_shouldReturnZero() {
        assertMaxProfitIs(new int[]{1, 2, 10}, 0, 0);
    }

    @Test
    void ifMaxTransactionsIsOne_shouldReturnTheDifferenceBetweenLowestAndHighestPrices() {
        assertMaxProfitIs(new int[]{5, 2, 9, 11, 8}, 1, 11 - 2);
        assertMaxProfitIs(new int[]{1, 3, 8, 2}, 1, 8 - 1);
    }

    @Test
    void ifThereIsNoProfitableTransactions_shouldReturnZero() {
        assertMaxProfitIs(new int[]{8, 6, 2, 1}, 2, 0);
    }

    @Test
    void ifMaxProfitComesFromLesserNumberTransactionsThanAllowed_shouldReturnProfitFromSingleTransaction() {
        assertMaxProfitIs(new int[]{5, 2, 7, 8, 78, 54}, 2, 78 - 2);
    }

    @Test
    void forTheSamePricesAndDifferentMaxTransactions_mayReturnProfitsFromVariousRanges() {
        assertMaxProfitIs(new int[]{6, 4, 9, 2, 17, 16, 10, 20, 7, 23}, 1, 23 - 2);
        assertMaxProfitIs(new int[]{6, 4, 9, 2, 17, 16, 10, 20, 7, 23}, 2, 20 - 2 + 23 - 7);
        assertMaxProfitIs(new int[]{6, 4, 9, 2, 17, 16, 10, 20, 7, 23}, 3, 17 - 2 + 23 - 7 + 20 - 10);
    }

    @Test
    void someOtherCases() {
        assertMaxProfitIs(new int[]{5, 11, 3, 50, 60, 90}, 2, 11 - 5 + 90 - 3);
        assertMaxProfitIs(new int[]{1, 10}, 3, 10 - 1);
        assertMaxProfitIs(new int[]{3, 10, 1, 7}, 1, 10 - 3);
    }

    private void assertMaxProfitIs(int[] prices, int maxTransactions, int expectedMaxProfit) {
        assertThat(maxProfit(prices, maxTransactions)).isEqualTo(expectedMaxProfit);
    }
}