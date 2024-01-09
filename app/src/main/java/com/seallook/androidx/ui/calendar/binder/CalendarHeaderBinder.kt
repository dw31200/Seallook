package com.seallook.androidx.ui.calendar.binder

import android.view.View
import android.widget.TextView
import androidx.core.view.children
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.seallook.androidx.R
import com.seallook.androidx.ui.calendar.widget.setTextColorRes
import java.time.DayOfWeek

class CalendarHeaderBinder(
    private val daysOfWeek: MutableList<DayOfWeek> = mutableListOf(),
) : MonthHeaderFooterBinder<CalendarHeaderViewContainer> {
    override fun create(view: View) = CalendarHeaderViewContainer(view)

    override fun bind(container: CalendarHeaderViewContainer, data: CalendarMonth) {
        // Setup each header day text if we have not done that already.
        if (container.legendLayout.tag == null) {
            container.legendLayout.tag = data.yearMonth
            container.legendLayout.children.map { it as TextView }
                .forEachIndexed { index, tv ->
                    tv.text = daysOfWeek[index].name.first().toString()
                    tv.setTextColorRes(R.color.black)
                }
        }
    }

    fun fetchData(daysOfWeek: List<DayOfWeek>) {
        this.daysOfWeek.clear()
        this.daysOfWeek.addAll(daysOfWeek)
    }
}
