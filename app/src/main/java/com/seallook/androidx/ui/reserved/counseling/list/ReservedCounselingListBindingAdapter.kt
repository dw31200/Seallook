package com.seallook.androidx.ui.reserved.counseling.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import com.seallook.androidx.ui.reserved.counseling.list.adapter.ReservedCounselingListAdapter

@BindingAdapter("bind:setCounselingList", "bind:updateConfirm", "bind:userType", requireAll = true)
fun RecyclerView.setList(list: List<ReservationUiModel>?, reservedClientUpdateConfirm: ReservedClientUpdateConfirm, userType: UserTypeUiModel?) {
    (adapter as? ReservedCounselingListAdapter)
        ?.fetchData(
            list ?: emptyList(),
            userType,
        )
    (adapter as? ReservedCounselingListAdapter)?.reservedClientUpdateConfirm = reservedClientUpdateConfirm
}

@BindingAdapter("bind:setReservedCounselingConfirm")
fun TextView.setConfirm(confirm: Boolean) {
    if (confirm) {
        text = "예약 확정"
    } else {
        text = "예약 미확정"
    }
}

@BindingAdapter("bind:userType")
fun TextView.setEmailTitle(userType: UserTypeUiModel?) {
    when (userType?.userType) {
        UserTypeOption.CLIENT -> text = "상담사 이메일"
        UserTypeOption.COUNSELOR -> text = "내담자 이메일"
        else -> Unit
    }
}

@BindingAdapter("bind:setReservedCounselingConfirmButton")
fun MaterialButton.setReservedCounselingConfirmButton(confirm: Boolean) {
    if (confirm) {
        text = "취소"
    } else {
        text = "승인"
    }
}
