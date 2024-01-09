package com.seallook.androidx.ui.calendar.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.ReservationUiModel

class ScheduleAdapter(
    private val scheduleItems: MutableList<ReservationUiModel> = mutableListOf(),
    private val onClick: (ReservationUiModel) -> Unit,
) : RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScheduleViewHolder(parent)

    override fun getItemCount(): Int = scheduleItems.size

    override fun onBindViewHolder(viewHolder: ScheduleViewHolder, position: Int) {
        viewHolder.bind(scheduleItems[position], onClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(scheduleItems: List<ReservationUiModel>) {
        this.scheduleItems.clear()
        this.scheduleItems.addAll(scheduleItems)
        notifyDataSetChanged()
    }
}
