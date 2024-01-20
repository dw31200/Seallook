package com.seallook.androidx.ui.mypage.counselor.info.update.office

import com.seallook.androidx.ui.base.Effect

sealed interface UpdateOfficeEffect : Effect {
    object SuccessUpdateOfficeInfo : UpdateOfficeEffect

    object SuccessUpdateOfficeId : UpdateOfficeEffect
}
