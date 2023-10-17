package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import com.seallook.androidx.ui.mypage.counselor.info.update.office.OfficeNavigation
import timber.log.Timber

class UpdateOfficeAdapter(
    private val officeItems: MutableList<OfficeInfoUiModel> = mutableListOf(),
) : RecyclerView.Adapter<UpdateOfficeHolder>() {
    var officeNavigation: OfficeNavigation? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateOfficeHolder =
        UpdateOfficeHolder(parent)

    override fun getItemCount(): Int = officeItems.size

    override fun onBindViewHolder(holder: UpdateOfficeHolder, position: Int) {
        holder.bind(officeItems[position], officeNavigation)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(officeItems: List<OfficeInfoUiModel>) {
        this.officeItems.clear()
        Timber.d("$officeItems")
        this.officeItems.addAll(officeItems)
        notifyDataSetChanged()
    }
}
