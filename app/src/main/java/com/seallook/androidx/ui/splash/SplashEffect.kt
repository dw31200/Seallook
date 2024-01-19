package com.seallook.androidx.ui.splash

import com.seallook.androidx.ui.base.Effect

interface SplashEffect : Effect {
    object NavigateToMain : SplashEffect

    object NavigateToAuth : SplashEffect
}
