package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import com.seallook.androidx.ui.mypage.counselor.info.update.office.OfficeNavigation
import kotlinx.coroutines.launch
import timber.log.Timber

@BindingAdapter("bind:officeResultList")
fun RecyclerView.setOfficeResultList(list: List<OfficeInfoUiModel>) {
    this.findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
        Timber.d("$list")
        (adapter as? UpdateOfficeAdapter)?.fetchData(list ?: emptyList())
    }
}

@BindingAdapter("bind:onSelectButtonClickListener")
fun RecyclerView.setSelectButtonClickListener(officeNavigation: OfficeNavigation) {
    (adapter as? UpdateOfficeAdapter)?.officeNavigation = officeNavigation
}
