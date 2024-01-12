package com.seallook.androidx.ui.reserved.counseling.detail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.seallook.androidx.share.DetailTextType
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("bind:reservation", "bind:schedule", "bind:textType", requireAll = true)
fun TextView.setText(reservation: ReservationUiModel?, schedule: CounselingScheduleUiModel?, textType: DetailTextType) {
    if (reservation != null && schedule != null) {
        val localDateTime = LocalDateTime.parse(
            reservation.date,
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"),
        )
        when (textType) {
            DetailTextType.DATE -> {
                text = "일정 ${localDateTime.toLocalDate()}"
            }
            DetailTextType.TIME -> {
                text = "시간 ${localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
            }
            DetailTextType.PRICE -> {
                text = "금액 ${DecimalFormat("#,###").format(schedule.price)} 원"
            }
            else -> Unit
        }
    }
}
