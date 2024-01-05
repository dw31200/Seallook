package com.seallook.androidx.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import com.seallook.androidx.ui.home.adapter.OfficeListAdapter
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.KakaoSearchUiModel

@BindingAdapter("bind:setList")
fun RecyclerView.setList(list: List<CounselorInfoUiModel>?) {
    (adapter as? HomeAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:setOfficeList")
fun RecyclerView.setOfficeList(list: List<KakaoSearchUiModel>?) {
    (adapter as? OfficeListAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:setDistance")
fun TextView.setDistance(distance: String) {
    text = "${distance}m"
}

@BindingAdapter("bind:onOfficeItemClickListener")
fun RecyclerView.setOnOfficeItemClickListener(homeShowWebSite: HomeShowWebSite) {
    (adapter as? OfficeListAdapter)?.homeShowWebSite = homeShowWebSite
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setCounselorItemClickListener(homeNavigation: HomeNavigation) {
    (adapter as? HomeAdapter)?.homeNavigation = homeNavigation
}

@BindingAdapter("bind:onReservedClientListButtonClickListener", "bind:userEmail", requireAll = true)
fun MaterialButton.setOnReservedClientListButtonClickListener(homeNavigation: HomeNavigation, userEmail: String?) {
    setOnClickListener {
        if (userEmail != null) {
            homeNavigation.navigateToReservedClient(userEmail)
        }
    }
}

@BindingAdapter("bind:onReservedCounselingListButtonClickListener", "bind:userEmail", requireAll = true)
fun MaterialButton.setOnReservedCounselingListButtonClickListener(homeNavigation: HomeNavigation, userEmail: String?) {
    setOnClickListener {
        if (userEmail != null) {
            homeNavigation.navigateToReservedCounseling(userEmail)
        }
    }
}
