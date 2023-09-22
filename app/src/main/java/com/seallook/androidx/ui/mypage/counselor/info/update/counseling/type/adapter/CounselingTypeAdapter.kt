package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.domain.model.CounselingTypeModel
import com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.DeleteCounselingType

class CounselingTypeAdapter(
    private val counselingTypeItems: MutableList<CounselingTypeModel> = mutableListOf(),
) : RecyclerView.Adapter<CounselingTypeHolder>() {
    var deleteCounselingType: DeleteCounselingType? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounselingTypeHolder = CounselingTypeHolder(parent)

    override fun getItemCount(): Int = counselingTypeItems.size

    override fun onBindViewHolder(holder: CounselingTypeHolder, position: Int) {
        holder.bind(counselingTypeItems[position], deleteCounselingType)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(counselingTypeItems: List<CounselingTypeModel>) {
        this.counselingTypeItems.clear()
        this.counselingTypeItems.addAll(counselingTypeItems)
        notifyDataSetChanged()
    }
}
