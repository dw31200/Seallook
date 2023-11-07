package com.seallook.androidx.ui.reserve.counseling.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel

class ReserveCounselingAdapter(
    private val counselingScheduleItems: MutableList<CounselingScheduleUiModel> = mutableListOf(),
    private val counselingTypeList: MutableList<CounselingTypeUiModel> = mutableListOf(),
) : RecyclerView.Adapter<ReserveCounselingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveCounselingHolder {
        return ReserveCounselingHolder(parent)
    }

    override fun getItemCount(): Int {
        return counselingScheduleItems.size
    }

    override fun onBindViewHolder(holder: ReserveCounselingHolder, position: Int) {
        holder.bind(counselingScheduleItems[position], counselingTypeList)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(
        counselingScheduleItems: List<CounselingScheduleUiModel>,
        counselingTypeList: List<CounselingTypeUiModel>,
    ) {
        this.counselingScheduleItems.clear()
        this.counselingScheduleItems.addAll(counselingScheduleItems)
        this.counselingTypeList.clear()
        this.counselingTypeList.addAll(counselingTypeList)
        notifyDataSetChanged()
    }
}
