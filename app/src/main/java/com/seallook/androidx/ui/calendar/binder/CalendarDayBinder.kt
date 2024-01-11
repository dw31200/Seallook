package com.seallook.androidx.ui.calendar.binder

import android.view.View
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.view.MonthDayBinder
import com.seallook.androidx.ui.model.ReservationUiModel
import java.time.LocalDate

class CalendarDayBinder(
    private val scheduleItems: MutableList<ReservationUiModel> = mutableListOf(),
    private val onClick: (LocalDate) -> Unit,
) : MonthDayBinder<CalendarDayViewContainer> {

    private lateinit var selectedDate: LocalDate
    override fun create(view: View) = CalendarDayViewContainer(view)

    override fun bind(container: CalendarDayViewContainer, data: CalendarDay) {
        container.bind(data, selectedDate, scheduleItems, onClick)
    }

    fun fetchData(selectedDate: LocalDate, scheduleItems: List<ReservationUiModel>) {
        this.selectedDate = selectedDate
        this.scheduleItems.clear()
        this.scheduleItems.addAll(scheduleItems)
    }
}
