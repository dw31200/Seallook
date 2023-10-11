package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.OfficeResultListItemBinding
import com.seallook.androidx.domain.model.NaverSearchModel
import com.seallook.androidx.domain.model.OfficeInfoModel
import com.seallook.androidx.ui.mypage.counselor.info.update.office.OfficeNavigation

class UpdateOfficeHolder(
    private val binding: OfficeResultListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(officeItem: NaverSearchModel, officeNavigation: OfficeNavigation?) {
        with(binding) {
            data = officeItem
            officeSelectButton.setOnClickListener {
                officeNavigation?.navigateToMypage(OfficeInfoModel(officeItem))
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): UpdateOfficeHolder {
            val binding = OfficeResultListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UpdateOfficeHolder(binding)
        }
    }
}
