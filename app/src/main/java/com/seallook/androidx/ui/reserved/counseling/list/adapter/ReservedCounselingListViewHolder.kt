package com.seallook.androidx.ui.reserved.counseling.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.seallook.androidx.databinding.ReservedCounselingListItemBinding
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserved.counseling.list.ReservedClientUpdateConfirm

class ReservedCounselingListViewHolder(
    private val binding: ReservedCounselingListItemBinding,
) : ReservationListViewHolder(binding.root) {
    override fun bind(
        reservationItem: ReservationUiModel,
        reservedClientUpdateConfirm: ReservedClientUpdateConfirm?,
        onClick: (String) -> Unit,
    ) {
        with(binding) {
            data = reservationItem
            binding.root.setOnClickListener {
                onClick(reservationItem.id)
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ReservedCounselingListViewHolder {
            val binding = ReservedCounselingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReservedCounselingListViewHolder(binding)
        }
    }
}
