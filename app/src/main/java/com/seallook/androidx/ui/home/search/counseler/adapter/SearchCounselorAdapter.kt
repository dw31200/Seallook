package com.seallook.androidx.ui.home.search.counseler.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselorInfoUiModel

class SearchCounselorAdapter(
    private val counselorInfoItems: MutableList<CounselorInfoUiModel> = mutableListOf(),
) : RecyclerView.Adapter<SearchCounselorHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCounselorHolder {
        return SearchCounselorHolder(parent)
    }

    override fun getItemCount(): Int {
        return counselorInfoItems.size
    }

    override fun onBindViewHolder(holder: SearchCounselorHolder, position: Int) {
        holder.bind(counselorInfoItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(counselorInfoItems: List<CounselorInfoUiModel>) {
        this.counselorInfoItems.clear()
        this.counselorInfoItems.addAll(counselorInfoItems)
        notifyDataSetChanged()
    }
}
