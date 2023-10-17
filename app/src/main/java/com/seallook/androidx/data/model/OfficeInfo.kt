package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.OfficeInfoEntity
import com.seallook.androidx.data.remote.model.OfficeInfoResponse

data class OfficeInfo(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: Int,
    val mapy: Int,
) {
    fun toEntity(id: Int): OfficeInfoEntity {
        return OfficeInfoEntity(
            id = id,
            title = title,
            link = link,
            category = category,
            description = description,
            telephone = telephone,
            address = address,
            roadAddress = roadAddress,
            mapx = mapx,
            mapy = mapy,
        )
    }

    fun toResponse(): OfficeInfoResponse {
        return OfficeInfoResponse(
            title = title,
            link = link,
            category = category,
            description = description,
            telephone = telephone,
            address = address,
            roadAddress = roadAddress,
            mapx = mapx,
            mapy = mapy,
        )
    }

    companion object {
        operator fun invoke(naverSearchApiResponse: OfficeInfoResponse): OfficeInfo {
            return OfficeInfo(
                title = naverSearchApiResponse.title,
                link = naverSearchApiResponse.link,
                category = naverSearchApiResponse.category,
                description = naverSearchApiResponse.description,
                telephone = naverSearchApiResponse.telephone,
                address = naverSearchApiResponse.address,
                roadAddress = naverSearchApiResponse.roadAddress,
                mapx = naverSearchApiResponse.mapx,
                mapy = naverSearchApiResponse.mapy,
            )
        }

        operator fun invoke(officeInfoEntity: OfficeInfoEntity): OfficeInfo {
            return OfficeInfo(
                title = officeInfoEntity.title,
                link = officeInfoEntity.link,
                category = officeInfoEntity.category,
                description = officeInfoEntity.description,
                telephone = officeInfoEntity.telephone,
                address = officeInfoEntity.address,
                roadAddress = officeInfoEntity.roadAddress,
                mapx = officeInfoEntity.mapx,
                mapy = officeInfoEntity.mapy,
            )
        }
    }
}
