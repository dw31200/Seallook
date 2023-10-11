package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.OfficeInfo

data class OfficeInfoModel(
    val info: NaverSearchModel,
) {
    fun toInfo(): OfficeInfo {
        return OfficeInfo(
            info = info.toInfo(),
        )
    }

    companion object {
        operator fun invoke(officeInfo: OfficeInfo): OfficeInfoModel {
            return OfficeInfoModel(
                info = NaverSearchModel(officeInfo.info),
            )
        }
    }
}
