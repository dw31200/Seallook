package com.seallook.androidx.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import com.seallook.androidx.ui.model.CounselorInfoUiModel

@BindingAdapter("bind:setList")
fun RecyclerView.setList(list: List<CounselorInfoUiModel>?) {
    (adapter as? HomeAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setCounselorItemClickListener(homeNavigation: HomeNavigation) {
    (adapter as? HomeAdapter)?.homeNavigation = homeNavigation
}
