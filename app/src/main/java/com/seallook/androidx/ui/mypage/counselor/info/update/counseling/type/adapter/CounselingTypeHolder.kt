package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.CounselingTypeListItemBinding
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.DeleteCounselingType

class CounselingTypeHolder(
    private val binding: CounselingTypeListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(counselingTypeItem: CounselingTypeUiModel, deleteCounselingType: DeleteCounselingType?) {
        with(binding) {
            data = counselingTypeItem
            counselingTypeDeleteButton.setOnClickListener {
                deleteCounselingType?.deleteCounselingType(counselingTypeItem.id)
            }
            executePendingBindings()
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): CounselingTypeHolder {
            val binding = CounselingTypeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CounselingTypeHolder(binding)
        }
    }
}
