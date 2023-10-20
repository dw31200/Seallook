package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.OfficeInfo

data class OfficeInfoModel(
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
    fun toInfo(): OfficeInfo {
        return OfficeInfo(
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
        operator fun invoke(officeInfo: OfficeInfo): OfficeInfoModel {
            return OfficeInfoModel(
                id = officeInfo.id,
                title = officeInfo.title,
                link = officeInfo.link,
                category = officeInfo.category,
                description = officeInfo.description,
                telephone = officeInfo.telephone,
                address = officeInfo.address,
                roadAddress = officeInfo.roadAddress,
                mapx = officeInfo.mapx,
                mapy = officeInfo.mapy,
            )
        }
    }
}
