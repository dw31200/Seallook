package com.seallook.androidx.ui.splash

import com.seallook.androidx.base.Effect

interface SplashEffect : Effect {
    object NavigateToMain : SplashEffect

    object NavigateToAuth : SplashEffect
}
