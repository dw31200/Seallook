package com.seallook.androidx.ui.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselorInfoUiModel

class HomeAdapter(
    private val counselorInfoItems: MutableList<CounselorInfoUiModel> = mutableListOf(),
) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return counselorInfoItems.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(counselorInfoItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(counselorInfoItems: List<CounselorInfoUiModel>) {
        this.counselorInfoItems.clear()
        this.counselorInfoItems.addAll(counselorInfoItems)
        notifyDataSetChanged()
    }
}
