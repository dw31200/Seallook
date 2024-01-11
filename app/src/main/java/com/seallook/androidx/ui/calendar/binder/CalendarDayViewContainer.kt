package com.seallook.androidx.ui.calendar.binder

import android.view.View
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.ViewContainer
import com.seallook.androidx.databinding.CalendarDayLayoutBinding
import com.seallook.androidx.ui.model.ReservationUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarDayViewContainer(view: View) : ViewContainer(view) {
    private var today: LocalDate = LocalDate.now()

    val binding = CalendarDayLayoutBinding.bind(view)

    fun bind(
        data: CalendarDay,
        selectedDate: LocalDate,
        scheduleItems: List<ReservationUiModel>,
        onClick: (LocalDate) -> Unit,
    ) {
        binding.data = data
        binding.today = today
        binding.selectedDate = selectedDate
        binding.checkedSchedule = scheduleItems.any {
            LocalDateTime.parse(
                it.date,
                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"),
            ).toLocalDate() == data.date
        }

        view.setOnClickListener {
            if (data.position == DayPosition.MonthDate) {
                onClick(data.date)
            }
        }
    }
}
