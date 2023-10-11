package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("bind:setTextFromCounselorInfo")
fun TextInputEditText.setTextFromCounselorInfo(counselorInfo: String?) {
    if (counselorInfo == null) setText("") else setText(counselorInfo)
}

@BindingAdapter("bind:setImageFromUrl")
fun ImageView.setImage(url: String?) {
    Glide
        .with(this)
        .load(url)
        .into(this)
}
