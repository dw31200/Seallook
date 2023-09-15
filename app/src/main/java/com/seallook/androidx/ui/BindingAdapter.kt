package com.seallook.androidx.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}