package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:countToString")
fun TextView.setTextFromCount(count: Int?) {
    text = "${count}명"
}

@BindingAdapter("bind:timeToString")
fun TextView.setTextFromTime(time: Int?) {
    text = "${time}분"
}

@BindingAdapter("bind:payToString")
fun TextView.setTextFromPay(pay: Int?) {
    text = "${pay}원"
}
