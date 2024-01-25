package com.seallook.androidx.ui.reserved.counseling.list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import com.seallook.androidx.ui.reserved.counseling.list.ReservedClientUpdateConfirm

abstract class ReservationListViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(
        reservationItem: ReservationUiModel,
        reservedClientUpdateConfirm: ReservedClientUpdateConfirm?,
        onClick: (String) -> Unit,
    )

    companion object {
        fun getViewType(userType: UserTypeUiModel?): Int {
            return when (userType?.userType) {
                UserTypeOption.CLIENT -> 0
                UserTypeOption.COUNSELOR -> 1
                UserTypeOption.OFFICE -> 2
                else -> 4
            }
        }

        fun getViewHolder(parent: ViewGroup, viewType: Int): ReservationListViewHolder {
            return when (viewType) {
                0 -> ReservedCounselingListViewHolder(parent)
                1 -> ReservedClientListViewHolder(parent)
                else -> throw IllegalStateException("Invalid view type")
            }
        }
    }
}
