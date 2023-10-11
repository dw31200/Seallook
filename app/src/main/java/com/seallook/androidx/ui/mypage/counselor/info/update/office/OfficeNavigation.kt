package com.seallook.androidx.ui.mypage.counselor.info.update.office

import com.seallook.androidx.domain.model.OfficeInfoModel

interface OfficeNavigation {
    fun navigateToMypage(info: OfficeInfoModel)
}