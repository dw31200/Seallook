package com.seallook.androidx.ui.mypage

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.seallook.androidx.ui.model.ProfileUiModel

@BindingAdapter("bind:profile", "bind:textType", requireAll = true)
fun TextView.setProfileText(profile: ProfileUiModel?, textType: MypageTextType) {
    when (textType) {
        MypageTextType.NAME -> text = "사용자 이름 : " + (profile?.name ?: "")
        MypageTextType.EMAIL -> text = "사용자 이메일 : " + (profile?.email ?: "")
    }
}
