package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.OfficeInfoEntity
import com.seallook.androidx.data.remote.model.OfficeInfoResponse

data class OfficeInfo(
    val info: NaverSearchInfo,
) {
    fun toEntity(id: Int): OfficeInfoEntity {
        return OfficeInfoEntity(
            id = id,
            title = info.title,
            link = info.link,
            category = info.category,
            description = info.description,
            telephone = info.telephone,
            address = info.address,
            roadAddress = info.roadAddress,
            mapx = info.mapx,
            mapy = info.mapy,
        )
    }
    companion object {
        operator fun invoke(officeInfoResponse: OfficeInfoResponse): OfficeInfo {
            return OfficeInfo(
                info = NaverSearchInfo(officeInfoResponse.info),
            )
        }

        operator fun invoke(officeInfoEntity: OfficeInfoEntity): OfficeInfo {
            return OfficeInfo(
                info = NaverSearchInfo(officeInfoEntity),
            )
        }
    }
}
