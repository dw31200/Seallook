package com.seallook.androidx.ui.reserved.counseling.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.share.DetailTextType
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.ProfileUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
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

@BindingAdapter("bind:setImageFromUrl", "bind:userType", requireAll = true)
fun ImageView.setImage(url: String?, userType: UserTypeUiModel?) {
    if (userType?.userType == UserTypeOption.CLIENT) {
        Glide
            .with(this)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("bind:officeWebSite", "bind:showWebSite", requireAll = true)
fun MaterialButton.setOnClick(officeWebSite: String?, showWebSite: ReservedCounselingDetailShowWebSite) {
    setOnClickListener {
        officeWebSite?.let {
            showWebSite.show(it)
        }
    }
}

@BindingAdapter("bind:counselorInfo", "bind:clientInfo", "bind:userType", "bind:textType", requireAll = true)
fun TextView.setInfoText(counselorInfo: CounselorInfoUiModel?, clientInfo: ProfileUiModel?, userType: UserTypeUiModel?, textType: ReservedCounselingDetailTextType) {
    when (textType) {
        ReservedCounselingDetailTextType.NAME -> {
            when (userType?.userType) {
                UserTypeOption.CLIENT -> text = counselorInfo?.name ?: ""
                UserTypeOption.COUNSELOR -> text = clientInfo?.name ?: ""
                else -> Unit
            }
        }
        ReservedCounselingDetailTextType.EMAIL -> {
            when (userType?.userType) {
                UserTypeOption.CLIENT -> text = counselorInfo?.email ?: ""
                UserTypeOption.COUNSELOR -> text = clientInfo?.email ?: ""
                else -> Unit
            }
        }
    }
}
