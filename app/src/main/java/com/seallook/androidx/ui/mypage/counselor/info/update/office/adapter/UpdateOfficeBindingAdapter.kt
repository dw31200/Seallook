package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.OfficeInfoUiModel

@BindingAdapter("bind:officeResultList")
fun RecyclerView.setOfficeResultList(list: List<OfficeInfoUiModel>?) {
    (adapter as? UpdateOfficeAdapter)?.fetchData(list ?: emptyList())
}
