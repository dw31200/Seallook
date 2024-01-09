package com.seallook.androidx.ui.calendar

import com.kizitonwose.calendar.core.CalendarMonth

interface CalendarScrollListener {
    fun onScrolled(calendarMonth: CalendarMonth)
}
