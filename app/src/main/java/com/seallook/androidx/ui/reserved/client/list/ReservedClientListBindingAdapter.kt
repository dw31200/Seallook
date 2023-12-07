package com.seallook.androidx.ui.reserved.client.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserved.client.list.adapter.ReservedClientListAdapter

@BindingAdapter("bind:setClientList")
fun RecyclerView.setList(list: List<ReservationUiModel>?) {
    (adapter as? ReservedClientListAdapter)?.fetchData(list ?: emptyList())
}
