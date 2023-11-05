package com.seallook.androidx.ui.reserve.counseling.calendar

import android.view.View
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.LocalDate

class ReserveCounselingCalendarBinder(
    private var selectedDate: LocalDate = LocalDate.now(),
) : WeekDayBinder<DayViewContainer> {
    var reserveCounselingSelectDate: ReserveCounselingSelectDate? = null
    override fun create(view: View) = DayViewContainer(view)
    override fun bind(container: DayViewContainer, data: WeekDay) {
        container.bind(selectedDate, data, reserveCounselingSelectDate)
    }

    fun selectDay(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
    }
}
