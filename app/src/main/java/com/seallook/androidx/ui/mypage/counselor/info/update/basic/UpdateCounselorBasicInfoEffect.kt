package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import com.seallook.androidx.base.Effect

sealed interface UpdateCounselorBasicInfoEffect : Effect {
    object SuccessUpdateCounselingType : UpdateCounselorBasicInfoEffect

    object SuccessUpdateCounselorInfo : UpdateCounselorBasicInfoEffect

    object SuccessUpdateOfficeInfo : UpdateCounselorBasicInfoEffect

    object FailureUpdateCounselingType : UpdateCounselorBasicInfoEffect

    object FailureUpdateCounselorInfo : UpdateCounselorBasicInfoEffect

    object FailureUpdateOfficeInfo : UpdateCounselorBasicInfoEffect
}
