package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.OfficeInfoEntity
import com.seallook.androidx.data.remote.model.OfficeInfoResponse

data class OfficeInfo(
    val id: Int,
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
    fun toLocalModel(): OfficeInfoEntity {
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

    fun toRemoteModel(): OfficeInfoResponse {
        return OfficeInfoResponse(
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

    companion object {
        operator fun invoke(officeInfoResponse: OfficeInfoResponse): OfficeInfo {
            return OfficeInfo(
                id = officeInfoResponse.id,
                title = officeInfoResponse.title,
                link = officeInfoResponse.link,
                category = officeInfoResponse.category,
                description = officeInfoResponse.description,
                telephone = officeInfoResponse.telephone,
                address = officeInfoResponse.address,
                roadAddress = officeInfoResponse.roadAddress,
                mapx = officeInfoResponse.mapx,
                mapy = officeInfoResponse.mapy,
            )
        }

        operator fun invoke(officeInfoEntity: OfficeInfoEntity): OfficeInfo {
            return OfficeInfo(
                id = officeInfoEntity.id,
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
