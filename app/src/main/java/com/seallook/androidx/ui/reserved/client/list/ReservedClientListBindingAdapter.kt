package com.seallook.androidx.ui.reserved.client.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserved.client.list.adapter.ReservedClientListAdapter

@BindingAdapter("bind:setClientList")
fun RecyclerView.setList(list: List<ReservationUiModel>?) {
    (adapter as? ReservedClientListAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:updateConfirm")
fun RecyclerView.updateConfirm(reservedClientUpdateConfirm: ReservedClientUpdateConfirm) {
    (adapter as? ReservedClientListAdapter)?.reservedClientUpdateConfirm = reservedClientUpdateConfirm
}

@BindingAdapter("bind:setReservedClintConfirm")
fun TextView.setConfirm(confirm: Boolean) {
    if (confirm) {
        text = "예약 확정"
    } else {
        text = "예약 미확정"
    }
}

@BindingAdapter("bind:setReservedClintConfirmButton")
fun MaterialButton.setReservedClintConfirmButton(confirm: Boolean) {
    if (confirm) {
        text = "취소하기"
    } else {
        text = "승인하기"
    }
}
