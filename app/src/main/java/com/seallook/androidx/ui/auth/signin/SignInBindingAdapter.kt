package com.seallook.androidx.ui.auth.signin

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton

@BindingAdapter("bind:setOnClickListenerSignIn", "bind:navigateType", requireAll = true)
fun MaterialButton.setOnClickListener(
    navigation: SignInNavigation,
    navigateType: NavigateType,
) {
    setOnClickListener {
        when (navigateType) {
            NavigateType.SIGN_IN_WITH_GOOGLE -> {
                navigation.signInWithGoogle()
            }

            NavigateType.SIGN_UP -> {
                navigation.navigateToSelectSignUpType()
            }
        }
    }
}

enum class NavigateType {
    SIGN_IN_WITH_GOOGLE,
    SIGN_UP,
}
