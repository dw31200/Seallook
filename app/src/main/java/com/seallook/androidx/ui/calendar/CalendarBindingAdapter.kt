package com.seallook.androidx.ui.calendar

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.CalendarView
import com.seallook.androidx.R
import com.seallook.androidx.ui.calendar.adapter.ScheduleAdapter
import com.seallook.androidx.ui.calendar.binder.CalendarDayBinder
import com.seallook.androidx.ui.calendar.binder.CalendarHeaderBinder
import com.seallook.androidx.ui.calendar.widget.makeInVisible
import com.seallook.androidx.ui.calendar.widget.makeVisible
import com.seallook.androidx.ui.calendar.widget.setTextColorRes
import com.seallook.androidx.ui.model.ReservationUiModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@BindingAdapter("bind:scheduleList", "bind:calendarSelectedDate", requireAll = true)
fun RecyclerView.setScheduleList(list: List<ReservationUiModel>?, selectedDate: LocalDate?) {
    (adapter as? ScheduleAdapter)?.fetchData(
        list?.filter {
            LocalDateTime.parse(
                it.date,
                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"),
            ).toLocalDate() == selectedDate
        } ?: emptyList(),
    )
}

@BindingAdapter("bind:today", "bind:calendarSelectedDate", "bind:calendarScheduleList", requireAll = true)
fun CalendarView.fetch(today: LocalDate, selectedDate: LocalDate?, list: List<ReservationUiModel>?) {
    (dayBinder as? CalendarDayBinder)?.fetchData(selectedDate ?: today, list ?: emptyList())
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

@BindingAdapter("bind:dayViewData", "bind:dayViewToday", "bind:dayViewSelectedDate", requireAll = true)
fun TextView.setDayViewText(data: CalendarDay, today: LocalDate?, dayViewSelectedDate: LocalDate?) {
    text = data.date.dayOfMonth.toString()
    if (data.position == DayPosition.MonthDate) {
        makeVisible()
        when (data.date) {
            today -> {
                setTextColorRes(R.color.white)
                setBackgroundResource(R.drawable.today_bg)
            }

            dayViewSelectedDate -> {
                setTextColorRes(R.color.blue)
                setBackgroundResource(R.drawable.selected_bg)
            }

            else -> {
                setTextColorRes(R.color.black)
                background = null
            }
        }
    } else {
        makeInVisible()
    }
}

@BindingAdapter("bind:dayViewData", "bind:dayViewToday", "bind:dayViewSelectedDate", "bind:checkedSchedule", requireAll = true)
fun View.setDayViewDot(
    data: CalendarDay,
    today: LocalDate?,
    dayViewSelectedDate: LocalDate?,
    checkedSchedule: Boolean,
) {
    if (data.position == DayPosition.MonthDate) {
        when (data.date) {
            today -> {
                makeInVisible()
            }

            dayViewSelectedDate -> {
                makeInVisible()
            }

            else -> {
                isVisible = checkedSchedule
            }
        }
    } else {
        makeInVisible()
    }
}
