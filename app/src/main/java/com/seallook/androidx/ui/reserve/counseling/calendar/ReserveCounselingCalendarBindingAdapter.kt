package com.seallook.androidx.ui.reserve.counseling.calendar

import androidx.databinding.BindingAdapter
import com.kizitonwose.calendar.view.WeekCalendarView
import java.time.LocalDate

@BindingAdapter("bind:reserveSelectedDate")
fun WeekCalendarView.setSelectedDate(selectedDate: LocalDate?) {
    (dayBinder as? ReserveCounselingCalendarBinder)?.selectDay(selectedDate ?: LocalDate.now())
    notifyCalendarChanged()
}

@BindingAdapter("bind:setOnClickDay")
fun WeekCalendarView.setOnClickDay(reserveCounselingSelectDate: ReserveCounselingSelectDate) {
    (dayBinder as? ReserveCounselingCalendarBinder)?.reserveCounselingSelectDate = reserveCounselingSelectDate
}
