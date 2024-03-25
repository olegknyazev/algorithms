package com.olegknyazev.sorts;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

class QuickSortTest extends SortTestBase {
    @Override
    protected <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        return QuickSort.sort(collection, comparator);
    }
}