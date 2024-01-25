package com.seallook.androidx.ui.reserve.counseling.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserve.counseling.CounselingScheduleSelect
import java.time.LocalDate

class ReserveCounselingAdapter(
    private val counselingScheduleItems: MutableList<CounselingScheduleUiModel> = mutableListOf(),
    private val reservationList: MutableList<ReservationUiModel> = mutableListOf(),
    private var selectedDate: LocalDate = LocalDate.now(),
) : RecyclerView.Adapter<ReserveCounselingHolder>() {
    var counselingScheduleSelect: CounselingScheduleSelect? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveCounselingHolder {
        return ReserveCounselingHolder(parent)
    }

    override fun getItemCount(): Int {
        return counselingScheduleItems.size
    }

    override fun onBindViewHolder(holder: ReserveCounselingHolder, position: Int) {
        holder.bind(
            counselingScheduleItems[position],
            counselingScheduleSelect,
            reservationList,
            selectedDate,
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(
        counselingScheduleItems: List<CounselingScheduleUiModel>,
        reservationList: List<ReservationUiModel>,
        selectedDate: LocalDate,
    ) {
        this.counselingScheduleItems.clear()
        this.counselingScheduleItems.addAll(counselingScheduleItems)
        this.reservationList.clear()
        this.reservationList.addAll(reservationList)
        this.selectedDate = selectedDate
        notifyDataSetChanged()
    }
}
