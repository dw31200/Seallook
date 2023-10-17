package com.seallook.androidx.ui.auth.signin

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.R

// TODO 이렇게 사용하는게 맞을까요?
@BindingAdapter("bind:setOnClickListenerSignIn")
fun MaterialButton.setOnClickListener(navigation: SignInNavigation) {
    setOnClickListener {
        when (it) {
            findViewById<MaterialButton>(R.id.google_sign_in_button) -> {
                navigation.signInWithGoogle()
            }
            findViewById<MaterialButton>(R.id.email_sign_in_button) -> {
                navigation.signInWithEmailAndPassword()
            }
            findViewById<MaterialButton>(R.id.email_sign_up_button) -> {
                navigation.navigateToSelectSignUpType()
            }
        }
    }
}
