package com.seallook.androidx.ui.calendar.binder

import android.view.View
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.ViewContainer
import com.seallook.androidx.databinding.CalendarDayLayoutBinding
import java.time.LocalDate

class CalendarDayViewContainer(
    view: View,
    onClick: (LocalDate) -> Unit,
) : ViewContainer(view) {
    lateinit var day: CalendarDay // Will be set when this container is bound.

    val binding = CalendarDayLayoutBinding.bind(view)

    init {
        view.setOnClickListener {
            if (day.position == DayPosition.MonthDate) {
                onClick(day.date)
            }
        }
    }
}
