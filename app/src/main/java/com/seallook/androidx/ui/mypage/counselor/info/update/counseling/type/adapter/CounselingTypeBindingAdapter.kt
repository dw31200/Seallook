package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.domain.model.CounselingTypeModel
import kotlinx.coroutines.launch

@BindingAdapter("bind:countToString")
fun TextView.setTextFromCount(count: Int?) {
    text = "${count}명"
}

@BindingAdapter("bind:timeToString")
fun TextView.setTextFromTime(time: Int?) {
    text = "${time}분"
}

@BindingAdapter("bind:payToString")
fun TextView.setTextFromPay(pay: Int?) {
    text = "${pay}원"
}

@BindingAdapter("bind:counselingTypeList")
fun RecyclerView.setGalleryList(list: List<CounselingTypeModel>?) {
    this.findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
        (adapter as? CounselingTypeAdapter)?.fetchData(list ?: emptyList())
    }
}
