package com.seallook.androidx.ui.mypage

import com.seallook.androidx.ui.base.Effect

sealed interface MypageEffect : Effect {
    object SuccessSignOut : MypageEffect
}
