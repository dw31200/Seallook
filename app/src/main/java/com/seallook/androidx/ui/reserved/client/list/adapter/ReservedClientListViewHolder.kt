package com.seallook.androidx.ui.reserved.client.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.ReservedClientListItemBinding
import com.seallook.androidx.ui.model.ReservationUiModel

class ReservedClientListViewHolder(
    private val binding: ReservedClientListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(reservedClientItem: ReservationUiModel) {
        with(binding) {
            data = reservedClientItem
            reservedClientEmailText.text = reservedClientItem.clientEmail
            reservedClientDateText.text = reservedClientItem.date.toString()
            if (reservedClientItem.confirm) {
                reservedClientConfirmText.text = "예약 승인"
                reservedClientConfirmButton.text = "취소하기"
            } else {
                reservedClientConfirmText.text = "예약 미승인"
                reservedClientConfirmButton.text = "승인하기"
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
