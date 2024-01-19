package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import com.seallook.androidx.ui.base.Effect

sealed interface UpdateCounselorBasicInfoEffect : Effect {
    object SuccessUpdateCounselorInfo : UpdateCounselorBasicInfoEffect

    object SuccessUpdateOfficeInfo : UpdateCounselorBasicInfoEffect

    object FailureUpdateCounselorInfo : UpdateCounselorBasicInfoEffect

    object FailureUpdateOfficeInfo : UpdateCounselorBasicInfoEffect
}
