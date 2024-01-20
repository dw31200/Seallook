package com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import timber.log.Timber

class UpdateOfficeAdapter(
    private val officeItems: MutableList<OfficeInfoUiModel> = mutableListOf(),
    private val onClick: (OfficeInfoUiModel) -> Unit,
) : RecyclerView.Adapter<UpdateOfficeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateOfficeHolder =
        UpdateOfficeHolder(parent)

    override fun getItemCount(): Int = officeItems.size

    override fun onBindViewHolder(holder: UpdateOfficeHolder, position: Int) {
        holder.bind(officeItems[position], onClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(officeItems: List<OfficeInfoUiModel>) {
        this.officeItems.clear()
        Timber.d("$officeItems")
        this.officeItems.addAll(officeItems)
        notifyDataSetChanged()
    }
}
