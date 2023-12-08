package com.seallook.androidx.ui.reserved.counseling.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserved.counseling.list.adapter.ReservedCounselingListAdapter

@BindingAdapter("bind:setCounselingList")
fun RecyclerView.setList(list: List<ReservationUiModel>?) {
    (adapter as? ReservedCounselingListAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:setReservedCounselingConfirm")
fun TextView.setConfirm(confirm: Boolean) {
    if (confirm) {
        text = "예약 확정"
    } else {
        text = "예약 미확정"
    }
}
