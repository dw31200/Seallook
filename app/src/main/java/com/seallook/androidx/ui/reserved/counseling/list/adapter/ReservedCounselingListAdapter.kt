package com.seallook.androidx.ui.reserved.counseling.list.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import com.seallook.androidx.ui.reserved.counseling.list.ReservedClientUpdateConfirm

class ReservedCounselingListAdapter(
    private val reservedCounselingItems: MutableList<ReservationUiModel> = mutableListOf(),
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<ReservationListViewHolder>() {
    var userType: UserTypeUiModel? = null

    var reservedClientUpdateConfirm: ReservedClientUpdateConfirm? = null

    override fun getItemViewType(position: Int): Int {
        return ReservationListViewHolder.getViewType(userType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationListViewHolder {
        return ReservationListViewHolder.getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return reservedCounselingItems.size
    }

    override fun onBindViewHolder(holder: ReservationListViewHolder, position: Int) {
        holder.bind(reservedCounselingItems[position], reservedClientUpdateConfirm, onClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(reservedCounselingItems: List<ReservationUiModel>, userType: UserTypeUiModel?) {
        this.reservedCounselingItems.clear()
        this.reservedCounselingItems.addAll(reservedCounselingItems)
        userType?.let { this.userType = it }
        notifyDataSetChanged()
    }
}
