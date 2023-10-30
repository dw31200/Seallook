package com.seallook.androidx.ui.auth.signup

import com.seallook.androidx.base.Effect

sealed interface SignUpEffect : Effect {
    object NavigateToHome : SignUpEffect
}
