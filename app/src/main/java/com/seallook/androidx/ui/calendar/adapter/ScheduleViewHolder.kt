package com.seallook.androidx.ui.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.ScheduleListItemBinding
import com.seallook.androidx.ui.model.ReservationUiModel

class ScheduleViewHolder(
    private val binding: ScheduleListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(scheduleItem: ReservationUiModel?, onClick: (ReservationUiModel) -> Unit) {
        with(binding) {
            data = scheduleItem
            root.setOnClickListener {
                if (scheduleItem != null) {
                    onClick(scheduleItem)
                }
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ScheduleViewHolder {
            val binding = ScheduleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ScheduleViewHolder(binding)
        }
    }
}
