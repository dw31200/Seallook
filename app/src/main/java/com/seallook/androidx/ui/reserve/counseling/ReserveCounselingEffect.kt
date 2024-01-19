package com.seallook.androidx.ui.reserve.counseling

import com.seallook.androidx.ui.base.Effect

sealed interface ReserveCounselingEffect : Effect {
    object NavigateToHome : ReserveCounselingEffect
}
