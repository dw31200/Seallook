package com.seallook.androidx.ui.reserve.counseling

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserve.counseling.adapter.ReserveCounselingAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@BindingAdapter("bind:setCounselingSchedule", "bind:reservationList", "bind:selectedDate", requireAll = true)
fun RecyclerView.setCounselingSchedule(
    scheduleList: List<CounselingScheduleUiModel>?,
    reservationList: List<ReservationUiModel>?,
    selectedDate: LocalDate?,
) {
    (adapter as? ReserveCounselingAdapter)
        ?.fetchData(
            scheduleList ?: emptyList(),
            reservationList ?: emptyList(),
            selectedDate ?: LocalDate.now(),
        )
}

@BindingAdapter("bind:setDate", "bind:setTime", requireAll = true)
fun TextView.setTime(time: String, currentTime: Int) {
    val format = SimpleDateFormat("HH:mm")
    val startTime = format.parse(time)
    val localDate = startTime.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    val nextLocalDate = localDate.plusMinutes(currentTime.toLong())
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

@BindingAdapter("bind:setEnabledBySelected")
fun MaterialButton.setEnabledBySelected(price: Int?) {
    if (price != null && price != 0) {
        isEnabled = true
    } else {
        isEnabled = false
    }
}
