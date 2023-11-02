package com.seallook.androidx.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.CounselorListItemBinding
import com.seallook.androidx.ui.model.CounselorInfoUiModel

class HomeHolder(
    private val binding: CounselorListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(counselorInfoItem: CounselorInfoUiModel) {
        with(binding) {
            data = counselorInfoItem
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): HomeHolder {
            val binding = CounselorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HomeHolder(binding)
        }
    }
}
