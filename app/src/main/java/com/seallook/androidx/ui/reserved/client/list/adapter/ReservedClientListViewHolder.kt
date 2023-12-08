package com.seallook.androidx.ui.reserved.client.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.ReservedClientListItemBinding
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserved.client.list.ReservedClientUpdateConfirm

class ReservedClientListViewHolder(
    private val binding: ReservedClientListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(reservedClientItem: ReservationUiModel, reservedClientUpdateConfirm: ReservedClientUpdateConfirm?) {
        with(binding) {
            data = reservedClientItem
            reservedClientConfirmButton.setOnClickListener {
                if (reservedClientItem.confirm) {
                    reservedClientUpdateConfirm?.updateConfirm(reservedClientItem.id, false)
                } else {
                    reservedClientUpdateConfirm?.updateConfirm(reservedClientItem.id, true)
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
