package com.seallook.androidx.ui.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.home.HomeShowWebSite
import com.seallook.androidx.ui.model.KakaoSearchUiModel

class OfficeListAdapter(
    private val officeItems: MutableList<KakaoSearchUiModel> = mutableListOf(),
) : RecyclerView.Adapter<OfficeViewHolder>() {
    var homeShowWebSite: HomeShowWebSite? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OfficeViewHolder(parent)

    override fun getItemCount(): Int = officeItems.size

    override fun onBindViewHolder(holder: OfficeViewHolder, position: Int) {
        holder.bind(officeItems[position], homeShowWebSite)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(officeItems: List<KakaoSearchUiModel>) {
        this.officeItems.clear()
        this.officeItems.addAll(officeItems)
        notifyDataSetChanged()
    }
}
