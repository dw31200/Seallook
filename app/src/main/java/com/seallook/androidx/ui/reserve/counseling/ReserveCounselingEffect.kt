package com.seallook.androidx.ui.reserve.counseling

import com.seallook.androidx.base.Effect

sealed interface ReserveCounselingEffect : Effect {
    object NavigateToHome : ReserveCounselingEffect
}
