package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.domain.model.NaverSearchModel

class UpdateOfficeAdapter(
    private val officeItems: MutableList<NaverSearchModel> = mutableListOf(),
) : RecyclerView.Adapter<UpdateOfficeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateOfficeHolder =
        UpdateOfficeHolder(parent)

    override fun getItemCount(): Int = officeItems.size

    override fun onBindViewHolder(holder: UpdateOfficeHolder, position: Int) {
        holder.bind(officeItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(officeItems: List<NaverSearchModel>) {
        this.officeItems.clear()
        this.officeItems.addAll(officeItems)
        notifyDataSetChanged()
    }
}
