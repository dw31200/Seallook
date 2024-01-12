package com.seallook.androidx.ui.reserved.counseling.list.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.ReservationUiModel

class ReservedCounselingListAdapter(
    private val reservedCounselingItems: MutableList<ReservationUiModel> = mutableListOf(),
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<ReservedCounselingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservedCounselingListViewHolder {
        return ReservedCounselingListViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return reservedCounselingItems.size
    }

    override fun onBindViewHolder(holder: ReservedCounselingListViewHolder, position: Int) {
        holder.bind(reservedCounselingItems[position], onClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(reservedCounselingItems: List<ReservationUiModel>) {
        this.reservedCounselingItems.clear()
        this.reservedCounselingItems.addAll(reservedCounselingItems)
        notifyDataSetChanged()
    }
}
