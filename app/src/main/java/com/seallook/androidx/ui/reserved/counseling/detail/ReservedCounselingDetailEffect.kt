package com.seallook.androidx.ui.reserved.counseling.detail

import com.seallook.androidx.ui.base.Effect

sealed interface ReservedCounselingDetailEffect : Effect {
    object UpdateConfirm : ReservedCounselingDetailEffect

    object SuccessUpdateConfirm : ReservedCounselingDetailEffect

    object FailureUpdateConfirm : ReservedCounselingDetailEffect
}
