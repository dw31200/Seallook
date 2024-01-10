package com.seallook.androidx.ui.calendar

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendar.view.CalendarView
import com.seallook.androidx.ui.calendar.adapter.ScheduleAdapter
import com.seallook.androidx.ui.calendar.binder.CalendarDayBinder
import com.seallook.androidx.ui.calendar.binder.CalendarHeaderBinder
import com.seallook.androidx.ui.model.ReservationUiModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@BindingAdapter("bind:scheduleList")
fun RecyclerView.setScheduleList(list: List<ReservationUiModel>?) {
    (adapter as? ScheduleAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:today", "bind:selectedDate", "bind:calendarScheduleList", requireAll = true)
fun CalendarView.fetch(today: LocalDate, selectedDate: LocalDate?, list: List<ReservationUiModel>?) {
    (dayBinder as? CalendarDayBinder)?.fetchData(today, selectedDate ?: today, list ?: emptyList())
    notifyCalendarChanged()
}

@BindingAdapter("bind:selectedDateText")
fun TextView.setSelectedDate(selectedDate: LocalDate?) {
    if (selectedDate != null) {
        text = DateTimeFormatter.ofPattern("d MMM yyyy").format(selectedDate)
    }
}

@BindingAdapter("bind:currentMonth", "bind:startMonth", "bind:endMonth", "bind:daysOfWeek", requireAll = true)
fun CalendarView.setUp(currentMonth: YearMonth, startMonth: YearMonth, endMonth: YearMonth, daysOfWeek: List<DayOfWeek>) {
    setup(startMonth, endMonth, daysOfWeek.first())
    scrollToMonth(currentMonth)
    (monthHeaderBinder as? CalendarHeaderBinder)?.fetchData(daysOfWeek)
}

@BindingAdapter("bind:scrollListener")
fun CalendarView.setOnScrollListener(scrollListener: CalendarScrollListener) {
    monthScrollListener = {
        scrollListener.onScrolled(it)
    }
}

@BindingAdapter("bind:oldDate", "bind:temSelectedDate", requireAll = true)
fun CalendarView.notify(oldDate: LocalDate?, temSelectedDate: LocalDate?) {
    if (oldDate != null && temSelectedDate != null) {
        notifyDateChanged(oldDate)
        notifyDateChanged(temSelectedDate)
    }
}
