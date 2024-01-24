package com.seallook.androidx.ui.reserved.client.list

import com.seallook.androidx.ui.base.Effect

sealed interface ReservedClientListEffect : Effect {
    object UpdateConfirm : ReservedClientListEffect

    object SuccessUpdateConfirm : ReservedClientListEffect

    object FailureUpdateConfirm : ReservedClientListEffect
}
