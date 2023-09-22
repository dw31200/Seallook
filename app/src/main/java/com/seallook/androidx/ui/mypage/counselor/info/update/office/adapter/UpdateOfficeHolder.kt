package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.OfficeResultListItemBinding
import com.seallook.androidx.domain.model.NaverSearchModel

class UpdateOfficeHolder(
    private val binding: OfficeResultListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(officeItem: NaverSearchModel) {
        with(binding) {
            data = officeItem
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): UpdateOfficeHolder {
            val binding = OfficeResultListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UpdateOfficeHolder(binding)
        }
    }
}
