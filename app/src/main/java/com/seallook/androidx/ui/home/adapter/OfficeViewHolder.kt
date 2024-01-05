package com.seallook.androidx.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.OfficeListItemBinding
import com.seallook.androidx.ui.model.KakaoSearchUiModel

class OfficeViewHolder(
    private val binding: OfficeListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(officeItem: KakaoSearchUiModel?) {
        with(binding) {
            data = officeItem
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): OfficeViewHolder {
            val binding = OfficeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return OfficeViewHolder(binding)
        }
    }
}
