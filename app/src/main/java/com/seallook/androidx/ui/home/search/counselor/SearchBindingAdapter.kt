package com.seallook.androidx.ui.home.search.counselor

import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.seallook.androidx.ui.home.search.counselor.adapter.SearchCounselorAdapter
import com.seallook.androidx.ui.model.CounselorInfoUiModel

@BindingAdapter("bind:setSearchList")
fun RecyclerView.setList(list: List<CounselorInfoUiModel>?) {
    (adapter as? SearchCounselorAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setCounselorItemClickListener(searchCounselorNavigation: SearchCounselorNavigation) {
    (adapter as? SearchCounselorAdapter)?.searchCounselorNavigation = searchCounselorNavigation
}

@BindingAdapter("bind:searchQuery")
fun TextInputEditText.setSearchQuery(searchQuery: String?) {
    Unit
}

@InverseBindingAdapter(attribute = "bind:searchQuery", event = "changedListener")
fun TextInputEditText.getSearchQuery(): String {
    return text.toString()
}

@BindingAdapter("changedListener")
fun TextInputEditText.setListeners(
    attrChange: InverseBindingListener,
) {
    addTextChangedListener {
        if (it != null) {
            attrChange.onChange()
        }
    }
}
