package com.olegknyazev;

import org.junit.jupiter.api.Test;

import static com.olegknyazev.MultiStringSearch.multiStringSearch;
import static org.assertj.core.api.Assertions.assertThat;

class MultiStringSearchTest {
    @Test
    void forEmptyInput_returnsEmptyOutput() {
        assertThat(multiStringSearch("bigstring", new String[0])).isEmpty();
    }

    @Test
    void ifNoneOfStringsFound_returnsArraysOfFalse() {
        assertThat(multiStringSearch("bigstring", new String[]{"bigg", "ingg"})).containsExactly(false, false);
    }

    @Test
    void forFoundStrings_returnsTrue() {
        assertThat(multiStringSearch("bigstring", new String[]{"big", "string", "gig"})).containsExactly(true, true, false);
        assertThat(multiStringSearch("bigstring", new String[]{"ing", "gigster", "igstr"})).containsExactly(true, false, true);
        assertThat(multiStringSearch("bigstring", new String[]{"bi", "i", "ng"})).containsExactly(true, true, true);
        assertThat(multiStringSearch("bigstring", new String[]{"bigstring"})).containsExactly(true);
        assertThat(multiStringSearch("bbbaabb", new String[]{"bbaabb"})).containsExactly(true);
    }
}