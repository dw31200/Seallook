package com.seallook.androidx.ui.calendar.binder

import android.view.View
import androidx.core.view.isVisible
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.MonthDayBinder
import com.seallook.androidx.R
import com.seallook.androidx.ui.calendar.widget.makeInVisible
import com.seallook.androidx.ui.calendar.widget.makeVisible
import com.seallook.androidx.ui.calendar.widget.setTextColorRes
import com.seallook.androidx.ui.model.ReservationUiModel
import java.time.LocalDate

class CalendarDayBinder(
    private val scheduleItems: MutableList<ReservationUiModel> = mutableListOf(),
    private val onClick: (LocalDate) -> Unit,
) : MonthDayBinder<CalendarDayViewContainer> {
    private var today: LocalDate = LocalDate.now()

    private lateinit var selectedDate: LocalDate
    override fun create(view: View) = CalendarDayViewContainer(view, onClick)

    override fun bind(container: CalendarDayViewContainer, data: CalendarDay) {
        container.day = data
        val textView = container.binding.exThreeDayText
        val dotView = container.binding.exThreeDotView

        textView.text = data.date.dayOfMonth.toString()

        if (data.position == DayPosition.MonthDate) {
            textView.makeVisible()
            when (data.date) {
                today -> {
                    textView.setTextColorRes(R.color.white)
                    textView.setBackgroundResource(R.drawable.today_bg)
                    dotView.makeInVisible()
                }

                selectedDate -> {
                    textView.setTextColorRes(R.color.blue)
                    textView.setBackgroundResource(R.drawable.selected_bg)
                    dotView.makeInVisible()
                }

                else -> {
                    textView.setTextColorRes(R.color.black)
                    textView.background = null
                    dotView.isVisible = scheduleItems.isNotEmpty()
                }
            }
        } else {
            textView.makeInVisible()
            dotView.makeInVisible()
        }
    }

    fun fetchData(today: LocalDate, selectedDate: LocalDate, scheduleItems: List<ReservationUiModel>) {
        this.today = today
        this.selectedDate = selectedDate
        this.scheduleItems.clear()
        this.scheduleItems.addAll(scheduleItems)
    }
}
