package com.seallook.androidx.ui.home.search.counseler

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.home.search.counseler.adapter.SearchCounselorAdapter
import com.seallook.androidx.ui.model.CounselorInfoUiModel

@BindingAdapter("bind:setSearchList")
fun RecyclerView.setList(list: List<CounselorInfoUiModel>?) {
    (adapter as? SearchCounselorAdapter)?.fetchData(list ?: emptyList())
}
