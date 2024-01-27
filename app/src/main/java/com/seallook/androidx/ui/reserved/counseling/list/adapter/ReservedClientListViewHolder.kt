package com.seallook.androidx.ui.reserved.counseling.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.seallook.androidx.databinding.ReservedClientListItemBinding
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserved.counseling.list.ReservedClientUpdateConfirm

class ReservedClientListViewHolder(
    private val binding: ReservedClientListItemBinding,
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
            reservedClientConfirmButton.setOnClickListener {
                if (reservationItem.confirm) {
                    reservedClientUpdateConfirm?.updateConfirm(reservationItem.id, false)
                } else {
                    reservedClientUpdateConfirm?.updateConfirm(reservationItem.id, true)
                }
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ReservedClientListViewHolder {
            val binding = ReservedClientListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReservedClientListViewHolder(binding)
        }
    }
}
