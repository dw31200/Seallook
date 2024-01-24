package com.seallook.androidx.ui.auth.signup

import com.seallook.androidx.ui.base.Effect

sealed interface SignUpEffect : Effect {
    object SignUp : SignUpEffect

    object FailureSignUp : SignUpEffect

    object FailureSetProfile : SignUpEffect

    object NavigateToHome : SignUpEffect
}
