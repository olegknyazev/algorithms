package com.olegknyazev

object CalendarMatching {
    // Returns the list of the time slots, big enough for having a meeting of meetingDuration minutes,
    // that are free in both calendars.
    // O(N + M) runtime, O(N) memory*
    // * Actually it's O(N) memory if we return Sequence instead of List. List is left for
    //   compatibility with the task checker.
    fun matchCalendars(
        calendar1: List<StringMeeting>,
        dailyBounds1: StringMeeting,
        calendar2: List<StringMeeting>,
        dailyBounds2: StringMeeting,
        meetingDuration: Int
    ): List<StringMeeting> {
        val freeSlots1 = freeRanges(dailyBounds1, calendar1)
        val freeSlots2 = freeRanges(dailyBounds2, calendar2)
        val i1 = freeSlots1.iterator()
        val i2 = freeSlots2.iterator()
        return sequence {
            if (!i1.hasNext() || !i2.hasNext())
                return@sequence
            var r1 = i1.next()
            var r2 = i2.next()
            while (true) {
                yield(r1 intersection r2)
                if (r1.end < r2.end) {
                    if (!i1.hasNext())
                        return@sequence
                    r1 = i1.next()
                } else {
                    if (!i2.hasNext())
                        return@sequence
                    r2 = i2.next()
                }
            }
        }
            .filterNot { it.isEmpty }
            .filter { it.length >= meetingDuration }
            .map(::toStringMeeting)
            .toList()
    }

    private fun freeRanges(bounds: StringMeeting, occupiedRanges: List<StringMeeting>): Sequence<TimeRange> {
        val startTime = timeFromString(bounds.start)
        val endTime = timeFromString(bounds.end)
        return (sequenceOf(startTime) +
                occupiedRanges.asSequence()
                    .map(::toRange)
                    .dropWhile { it.start < startTime }
                    .takeWhile { it.end <= endTime }
                    .flatMap { sequenceOf(it.start, it.end) } +
                sequenceOf(endTime)
                ).chunked(2) { (start, end) -> TimeRange(start, end) }

    }

    private fun toRange(stringMeeting: StringMeeting) =
        TimeRange.fromString(stringMeeting.start, stringMeeting.end)

    private fun toStringMeeting(timeRange: TimeRange) =
        StringMeeting(timeToString(timeRange.start), timeToString(timeRange.end))

    private fun timeFromString(time: String): Int {
        val (hours, minutes) = time.split(":".toRegex()).map { it.toInt() }
        return hours * 60 + minutes
    }

    private fun timeToString(time: Int): String {
        val minutes = time % 60
        val hours = time / 60
        return "%d:%02d".format(hours, minutes)
    }

    private data class TimeRange(val start: Int, val end: Int) {
        val isEmpty: Boolean get() = start >= end
        val length: Int = end - start

        infix fun intersection(other: TimeRange) =
            TimeRange(maxOf(start, other.start), minOf(end, other.end))

        companion object {
            fun fromString(start: String, end: String) =
                TimeRange(timeFromString(start), timeFromString(end))
        }
    }

    data class StringMeeting(val start: String, val end: String)
}
