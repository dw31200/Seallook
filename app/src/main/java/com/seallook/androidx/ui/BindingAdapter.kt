package com.seallook.androidx.ui

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:visibleOrInvisible")
fun View.setVisibleOr(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:userTypeToHint")
fun TextInputLayout.setHint(userType: Int) {
    hint = if (userType == 2) "기관명" else "이름"
}

@BindingAdapter("bind:enable")
fun View.setEnable(enable: Boolean) {
    isEnabled = enable
}

@BindingAdapter("bind:setEnableProfileInfo")
fun View.setEnable(profileInfo: String?) {
    if (profileInfo == null) {
        isEnabled = true
    } else if (profileInfo.isNotBlank()) {
        isEnabled = false
    } else if (profileInfo.isBlank()) {
        isEnabled = true
    }
}
