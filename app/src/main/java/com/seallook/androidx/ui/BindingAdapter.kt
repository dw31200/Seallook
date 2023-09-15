package com.seallook.androidx.ui

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:userTypeToHint")
fun TextInputLayout.setHint(userType: Int) {
    hint = if (userType == 2) "기관명" else "이름"
}
