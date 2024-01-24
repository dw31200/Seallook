package com.seallook.androidx.ui.reserve.counseling.calendar

import android.view.View
import androidx.core.view.isVisible
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import com.seallook.androidx.R
import com.seallook.androidx.databinding.ReserveCounselingCalendarDayBinding
import com.seallook.androidx.ui.calendar.widget.getColorCompat
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.utils.displayText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DayViewContainer(view: View) : ViewContainer(view) {
    val binding = ReserveCounselingCalendarDayBinding.bind(view)

    private val dateFormatter = DateTimeFormatter.ofPattern("dd")

    fun bind(selectedDate: LocalDate, day: WeekDay, reserveCounselingSelectDate: ReserveCounselingSelectDate?, scheduleItems: List<CounselingScheduleUiModel>) {
        with(binding) {
            dayViewDot.isVisible = scheduleItems.any {
                day.date.dayOfWeek == it.repeatedDay
            } && day.date != selectedDate
            dateTextView.text = dateFormatter.format(day.date)
            dayTextView.text = day.date.dayOfWeek.displayText()
            val colorRes = if (day.date == selectedDate) {
                R.color.colorPrimary
            } else {
                R.color.black
            }
            dateTextView.setTextColor(view.context.getColorCompat(colorRes))
            selectedView.isVisible = day.date == selectedDate
            root.setOnClickListener {
                if (selectedDate != day.date) {
                    reserveCounselingSelectDate?.selectDate(day.date)
                }
            }
        }
    }
}
