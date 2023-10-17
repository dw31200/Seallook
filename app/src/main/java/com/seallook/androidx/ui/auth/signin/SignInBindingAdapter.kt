package com.seallook.androidx.ui.auth.signin

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton

// TODO 이렇게 사용하는게 맞을까요?
@BindingAdapter("bind:setOnClickListenerSignIn", "bind:navigationType", requireAll = true)
fun MaterialButton.setOnClickListener(
    navigation: SignInNavigation,
    navigationType: SignInNavigationType,
) {
    setOnClickListener {
        when (navigationType) {
            SignInNavigationType.SIGN_IN_WITH_GOOGLE -> {
                navigation.signInWithGoogle()
            }

            SignInNavigationType.SIGN_IN_WITH_EMAIL_AND_PASSWORD -> {
                navigation.navigateToSelectSignUpType()
            }

            SignInNavigationType.SIGN_UP_WITH_EMAIL_AND_PASSWORD -> {
                navigation.signInWithEmailAndPassword()
            }
        }
    }
}

enum class SignInNavigationType {
    SIGN_IN_WITH_GOOGLE,
    SIGN_IN_WITH_EMAIL_AND_PASSWORD,
    SIGN_UP_WITH_EMAIL_AND_PASSWORD,
}
