package com.seallook.androidx.ui.auth.signup

import com.seallook.androidx.ui.base.Effect

sealed interface SignUpEffect : Effect {
    object NavigateToHome : SignUpEffect
}
