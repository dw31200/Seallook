package com.seallook.androidx.ui.reserve.counseling.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.CounselingDateSelectorListItemBinding
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserve.counseling.CounselingScheduleSelect
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class ReserveCounselingHolder(
    private val binding: CounselingDateSelectorListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        counselingScheduleItem: CounselingScheduleUiModel?,
        counselingScheduleSelect: CounselingScheduleSelect?,
        reservationList: List<ReservationUiModel>,
        selectedDate: LocalDate,
    ) {
        val instant = selectedDate.atStartOfDay(ZoneId.systemDefault())?.toInstant()
        val dateString = Date.from(instant)
        val formatter = SimpleDateFormat("yyyy.MM.dd")
        val selectedDateString = formatter.format(dateString) + " " + counselingScheduleItem?.time
        with(binding) {
            schedule = counselingScheduleItem
            reservation = reservationList.find {
                it.date == selectedDateString
            }
            counselingSelectorLayout.setOnClickListener {
                if (reservationList.find {
                        it.date == selectedDateString
                    }?.confirm == true
                ) {
                    return@setOnClickListener
                }
                counselingScheduleSelect?.selectSchedule(counselingScheduleItem)
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ReserveCounselingHolder {
            val binding = CounselingDateSelectorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReserveCounselingHolder(binding)
        }
    }
}
