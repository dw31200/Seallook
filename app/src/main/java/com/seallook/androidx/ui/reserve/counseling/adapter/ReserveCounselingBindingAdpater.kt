package com.seallook.androidx.ui.reserve.counseling.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@BindingAdapter("bind:setCounselingSchedule", "bind:setCounselingType", requireAll = true)
fun RecyclerView.setCounselingSchedule(
    scheduleList: List<CounselingScheduleUiModel>?,
    typeList: List<CounselingTypeUiModel>?,
) {
    (adapter as? ReserveCounselingAdapter)
        ?.fetchData(
            scheduleList ?: emptyList(),
            typeList ?: emptyList(),
        )
}

@BindingAdapter("bind:setDate", "bind:setTime", requireAll = true)
fun TextView.setTime(date: Date, time: Int) {
    val localDate = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    val nextLocalDate = localDate.plusMinutes(time.toLong())
    DateTimeFormatter.ofPattern("HH:mm").format(localDate)
    text =
        DateTimeFormatter.ofPattern("HH:mm").format(localDate) + " ~ " + DateTimeFormatter.ofPattern("HH:mm").format(nextLocalDate)
}
