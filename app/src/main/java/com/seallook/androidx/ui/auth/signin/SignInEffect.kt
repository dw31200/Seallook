package com.seallook.androidx.ui.auth.signin

import com.seallook.androidx.ui.base.Effect

sealed interface SignInEffect : Effect {
    object SuccessSignIn : SignInEffect

    object FailureSignIn : SignInEffect

    object SignInWithGoogle : SignInEffect

    object SuccessSignInWithGoogle : SignInEffect

    object FailureSignInWithGoogle : SignInEffect

    object SignInWithEmailAndPassword : SignInEffect

    object SuccessSignInWithEmailAndPassword : SignInEffect

    object FailureSignInWithEmailAndPassword : SignInEffect

    object NavigateToSignUp : SignInEffect
}
