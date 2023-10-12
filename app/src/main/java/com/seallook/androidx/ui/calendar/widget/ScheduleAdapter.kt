package com.seallook.androidx.ui.calendar.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.ScheduleListItemBinding

class ScheduleAdapter(val onClick: (Schedule) -> Unit) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    val scheduleList = mutableListOf<Schedule>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            ScheduleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(viewHolder: ScheduleViewHolder, position: Int) {
        viewHolder.bind(scheduleList[position])
    }

    override fun getItemCount(): Int = scheduleList.size

    inner class ScheduleViewHolder(private val binding: ScheduleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(scheduleList[bindingAdapterPosition])
            }
        }

        fun bind(event: Schedule) {
            binding.itemEventText.text = event.text
        }
    }
}
