package com.seallook.androidx.ui.auth.signin

import com.seallook.androidx.ui.base.Effect

sealed interface SignInEffect : Effect {
    object NavigateToHome : SignInEffect

    object NavigateToSignUp : SignInEffect
}
