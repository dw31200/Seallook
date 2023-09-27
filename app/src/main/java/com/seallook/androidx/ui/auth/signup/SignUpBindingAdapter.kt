package com.seallook.androidx.ui.auth.signup

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:setTextInProfileInfo")
fun TextInputEditText.setTextInProfileInfo(profileInfo: String?) {
    if (profileInfo == null) setText("") else setText(profileInfo)
}

@BindingAdapter("bind:setErrorMessage")
fun TextInputLayout.setErrorMessage(error: String?) {
    if (error != null) setError(error)
}
