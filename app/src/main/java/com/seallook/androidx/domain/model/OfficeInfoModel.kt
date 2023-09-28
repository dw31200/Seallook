package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.OfficeInfo

data class OfficeInfoModel(
    val uid: String,
    val info: NaverSearchModel,
) {
    companion object {
        operator fun invoke(officeInfo: OfficeInfo): OfficeInfoModel {
            return OfficeInfoModel(
                uid = officeInfo.uid,
                info = NaverSearchModel(officeInfo.info),
            )
        }
    }
}
