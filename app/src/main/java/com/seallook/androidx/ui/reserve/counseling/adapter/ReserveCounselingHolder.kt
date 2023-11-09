package com.seallook.androidx.ui.reserve.counseling.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.CounselingDateSelectorListItemBinding
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import com.seallook.androidx.ui.reserve.counseling.CounselingScheduleSelect

class ReserveCounselingHolder(
    private val binding: CounselingDateSelectorListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        counselingScheduleItem: CounselingScheduleUiModel,
        counselingTypeList: List<CounselingTypeUiModel>,
        counselingScheduleSelect: CounselingScheduleSelect?,
    ) {
        with(binding) {
            schedule = counselingScheduleItem
            if (counselingTypeList.isNotEmpty()) {
                type = counselingTypeList[counselingScheduleItem.typeId]
            }
            counselingSelectorLayout.setOnClickListener {
                if (counselingScheduleItem.reservation) {
                    counselingScheduleSelect?.selectSchedule(0)
                } else {
                    counselingScheduleSelect?.selectSchedule(counselingTypeList[counselingScheduleItem.typeId].price)
                }
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