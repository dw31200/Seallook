package com.seallook.androidx.ui.auth.signup

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("bind:setTextInProfileInfo")
fun TextInputEditText.setTextInProfileInfo(profileInfo: String?) {
    if (profileInfo == null) setText("") else setText(profileInfo)
}
