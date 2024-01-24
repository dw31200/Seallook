package com.seallook.androidx.ui.reserve.counseling.calendar

import android.view.View
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.WeekDayBinder
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import java.time.LocalDate

class ReserveCounselingCalendarBinder(
    private var selectedDate: LocalDate = LocalDate.now(),
    private val scheduleItems: MutableList<CounselingScheduleUiModel> = mutableListOf(),
) : WeekDayBinder<DayViewContainer> {
    var reserveCounselingSelectDate: ReserveCounselingSelectDate? = null

    override fun create(view: View) = DayViewContainer(view)

    override fun bind(container: DayViewContainer, data: WeekDay) {
        container.bind(selectedDate, data, reserveCounselingSelectDate, scheduleItems)
    }

    fun selectDay(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
    }

    fun setScheduleItems(scheduleItems: List<CounselingScheduleUiModel>) {
        this.scheduleItems.clear()
        this.scheduleItems.addAll(scheduleItems)
    }
}
