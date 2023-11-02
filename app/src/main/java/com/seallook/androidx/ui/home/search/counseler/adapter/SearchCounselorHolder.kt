package com.seallook.androidx.ui.home.search.counseler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.databinding.CounselorListItemBinding
import com.seallook.androidx.ui.home.search.counseler.SearchCounselorNavigation
import com.seallook.androidx.ui.model.CounselorInfoUiModel

class SearchCounselorHolder(
    private val binding: CounselorListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(counselorInfoItem: CounselorInfoUiModel?, searchCounselorNavigation: SearchCounselorNavigation?) {
        with(binding) {
            data = counselorInfoItem
            counselorThumbnailImage.setOnClickListener {
                if (counselorInfoItem != null) {
                    searchCounselorNavigation?.navigateToReserveCounseling(counselorInfoItem.email)
                }
            }
            executePendingBindings()
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): SearchCounselorHolder {
            val binding = CounselorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchCounselorHolder(binding)
        }
    }
}
