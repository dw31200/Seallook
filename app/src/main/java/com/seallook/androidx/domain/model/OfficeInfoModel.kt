package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.OfficeInfo

data class OfficeInfoModel(
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
    fun toDataModel(): OfficeInfo {
        return OfficeInfo(
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
        operator fun invoke(officeInfo: OfficeInfo): OfficeInfoModel {
            return OfficeInfoModel(
                id = officeInfo.id,
                place_name = officeInfo.place_name,
                phone = officeInfo.phone,
                address_name = officeInfo.address_name,
                road_address_name = officeInfo.road_address_name,
                x = officeInfo.x,
                y = officeInfo.y,
                place_url = officeInfo.place_url,
                distance = officeInfo.distance,
            )
        }
    }
}
