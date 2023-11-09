package com.seallook.androidx.ui.reserve.counseling

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import com.seallook.androidx.ui.reserve.counseling.adapter.ReserveCounselingAdapter
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
    text =
        DateTimeFormatter.ofPattern("HH:mm").format(localDate) + " ~ " + DateTimeFormatter.ofPattern("HH:mm").format(nextLocalDate)
}

@BindingAdapter("bind:setScheduleItemClickListener")
fun RecyclerView.setScheduleItemClickListener(counselingScheduleSelect: CounselingScheduleSelect) {
    (adapter as? ReserveCounselingAdapter)?.counselingScheduleSelect = counselingScheduleSelect
}

@BindingAdapter("bind:setPrice")
fun TextView.setPrice(price: Int?) {
    text = price.toString() + "원"
    if (price == 0 || price == null) {
        visibility = View.INVISIBLE
    } else {
        visibility = View.VISIBLE
    }
}

@BindingAdapter("bind:setReservation")
fun TextView.setReservation(reservation: Boolean?) {
    if (reservation == true) {
        text = "모집마감"
    } else {
        text = "모집중"
    }
}
