package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.OfficeInfoResponse

data class OfficeInfo(
    val uid: String,
    val info: NaverSearchInfo,
) {
    companion object {
        operator fun invoke(officeInfoResponse: OfficeInfoResponse): OfficeInfo {
            return OfficeInfo(
                uid = officeInfoResponse.uid,
                info = NaverSearchInfo(officeInfoResponse.info),
            )
        }
    }
}
