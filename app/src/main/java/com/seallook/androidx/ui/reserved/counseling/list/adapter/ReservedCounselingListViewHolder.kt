package com.seallook.androidx.ui.reserved.counseling.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.ReservedCounselingListItemBinding
import com.seallook.androidx.ui.model.ReservationUiModel

class ReservedCounselingListViewHolder(
    private val binding: ReservedCounselingListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(reservedCounselingItem: ReservationUiModel, onClick: (String) -> Unit) {
        with(binding) {
            data = reservedCounselingItem
            binding.root.setOnClickListener {
                onClick(reservedCounselingItem.id)
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
