package com.seallook.androidx.ui.reserved.client.list.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.ReservationUiModel

class ReservedClientListAdapter(
    private val reservedClientItems: MutableList<ReservationUiModel> = mutableListOf(),
) : RecyclerView.Adapter<ReservedClientListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservedClientListViewHolder {
        return ReservedClientListViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return reservedClientItems.size
    }

    override fun onBindViewHolder(holder: ReservedClientListViewHolder, position: Int) {
        holder.bind(reservedClientItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(reservedClientItems: List<ReservationUiModel>) {
        this.reservedClientItems.clear()
        this.reservedClientItems.addAll(reservedClientItems)
        notifyDataSetChanged()
    }
}
