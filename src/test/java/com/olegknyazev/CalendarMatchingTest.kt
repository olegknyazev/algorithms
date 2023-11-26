package com.olegknyazev

import com.olegknyazev.CalendarMatching.StringMeeting
import com.olegknyazev.CalendarMatching.matchCalendars
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CalendarMatchingTest {
    @Test
    fun `for non-intersecting work days, should return an empty list`() {
        assertThat(
            matchCalendars(
                listOf(),
                meeting("9:00", "12:30"),
                listOf(),
                meeting("14:00", "17:00"),
                30
            )
        ).isEmpty()
    }

    @Test
    fun `for completely free work days, should return their intersection`() {
        assertThat(
            matchCalendars(
                listOf(),
                meeting("9:00", "17:00"),
                listOf(),
                meeting("10:00", "18:00"),
                60
            )
        ).containsExactly(meeting("10:00", "17:00"))
    }

    @Test
    fun `should return all the intersections of the unoccupied time`() {
        assertThat(
            matchCalendars(
                listOf(meeting("10:00", "11:00"), meeting("13:30", "14:30")),
                meeting("9:00", "14:00"),
                listOf(meeting("10:30", "12:00"), meeting("13:00", "14:00")),
                meeting("9:30", "15:00"),
                30
            )
        ).containsExactly(meeting("9:30", "10:00"), meeting("12:00", "13:00"))
    }

    @Test
    fun `should not returns the intersections that are too small for the required meeting duration`() {
        assertThat(
            matchCalendars(
                listOf(meeting("12:00", "13:30")),
                meeting("11:00", "15:00"),
                listOf(meeting("9:30", "10:30"), meeting("14:45", "16:00")),
                meeting("9:00", "16:00"),
                90
            )
        ).isEmpty()
    }

    private fun meeting(start: String, end: String) = StringMeeting(start, end)
}