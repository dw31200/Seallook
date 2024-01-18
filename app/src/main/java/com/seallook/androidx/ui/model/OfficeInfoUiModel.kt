package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.KakaoSearchModel
import com.seallook.androidx.domain.model.OfficeInfoModel

data class OfficeInfoUiModel(
    val id: String,
    val place_name: String,
    val phone: String,
    val address_name: String,
    val road_address_name: String,
    val x: String,
    val y: String,
    val place_url: String,
    val distance: String,
) {
    fun toDomainModel(): OfficeInfoModel {
        return OfficeInfoModel(
            id = id,
            place_name = place_name,
            phone = phone,
            address_name = address_name,
            road_address_name = road_address_name,
            x = x,
            y = y,
            place_url = place_url,
            distance = distance,
        )
    }

    companion object {
        operator fun invoke(officeInfoModel: OfficeInfoModel): OfficeInfoUiModel {
            return OfficeInfoUiModel(
                id = officeInfoModel.id,
                place_name = officeInfoModel.place_name,
                phone = officeInfoModel.phone,
                address_name = officeInfoModel.address_name,
                road_address_name = officeInfoModel.road_address_name,
                x = officeInfoModel.x,
                y = officeInfoModel.y,
                place_url = officeInfoModel.place_url,
                distance = officeInfoModel.distance,
            )
        }

        operator fun invoke(kakaoSearchModel: KakaoSearchModel): OfficeInfoUiModel {
            return OfficeInfoUiModel(
                id = kakaoSearchModel.id,
                place_name = kakaoSearchModel.place_name,
                phone = kakaoSearchModel.phone,
                address_name = kakaoSearchModel.address_name,
                road_address_name = kakaoSearchModel.road_address_name,
                x = kakaoSearchModel.x,
                y = kakaoSearchModel.y,
                place_url = kakaoSearchModel.place_url,
                distance = kakaoSearchModel.distance,
            )
        }
    }
}
