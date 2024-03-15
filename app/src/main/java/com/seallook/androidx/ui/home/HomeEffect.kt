package com.seallook.androidx.ui.home

import com.seallook.androidx.ui.base.Effect

sealed interface HomeEffect : Effect {
    object SuccessGetCurrentLocation : HomeEffect

    object FailureGetCurrentLocation : HomeEffect

    object SecurityError : HomeEffect
}
