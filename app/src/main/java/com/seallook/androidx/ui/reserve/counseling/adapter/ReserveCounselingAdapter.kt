package com.seallook.androidx.ui.reserve.counseling.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.reserve.counseling.CounselingScheduleSelect

class ReserveCounselingAdapter(
    private val counselingScheduleItems: MutableList<CounselingScheduleUiModel> = mutableListOf(),
) : RecyclerView.Adapter<ReserveCounselingHolder>() {
    var counselingScheduleSelect: CounselingScheduleSelect? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveCounselingHolder {
        return ReserveCounselingHolder(parent)
    }

    override fun getItemCount(): Int {
        return counselingScheduleItems.size
    }

    override fun onBindViewHolder(holder: ReserveCounselingHolder, position: Int) {
        holder.bind(counselingScheduleItems[position], counselingScheduleSelect)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(
        counselingScheduleItems: List<CounselingScheduleUiModel>,
    ) {
        this.counselingScheduleItems.clear()
        this.counselingScheduleItems.addAll(counselingScheduleItems)
        notifyDataSetChanged()
    }
}
