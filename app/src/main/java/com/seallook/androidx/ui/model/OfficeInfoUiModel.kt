package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.OfficeInfoModel

data class OfficeInfoUiModel(
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
    fun toDomainModel(): OfficeInfoModel {
        return OfficeInfoModel(
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
        operator fun invoke(officeInfoModel: OfficeInfoModel): OfficeInfoUiModel {
            return OfficeInfoUiModel(
                id = officeInfoModel.id,
                title = officeInfoModel.title,
                link = officeInfoModel.link,
                category = officeInfoModel.category,
                description = officeInfoModel.description,
                telephone = officeInfoModel.telephone,
                address = officeInfoModel.address,
                roadAddress = officeInfoModel.roadAddress,
                mapx = officeInfoModel.mapx,
                mapy = officeInfoModel.mapy,
            )
        }
    }
}
