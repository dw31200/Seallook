package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.OfficeInfoEntity
import com.seallook.androidx.data.remote.model.OfficeInfoResponse

data class OfficeInfo(
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
    fun toLocalModel(): OfficeInfoEntity {
        return OfficeInfoEntity(
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

    fun toRemoteModel(): OfficeInfoResponse {
        return OfficeInfoResponse(
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
        operator fun invoke(officeInfoResponse: OfficeInfoResponse): OfficeInfo {
            return OfficeInfo(
                id = officeInfoResponse.id,
                place_name = officeInfoResponse.place_name,
                phone = officeInfoResponse.phone,
                address_name = officeInfoResponse.address_name,
                road_address_name = officeInfoResponse.road_address_name,
                x = officeInfoResponse.x,
                y = officeInfoResponse.y,
                place_url = officeInfoResponse.place_url,
                distance = officeInfoResponse.distance,
            )
        }

        operator fun invoke(officeInfoEntity: OfficeInfoEntity): OfficeInfo {
            return OfficeInfo(
                id = officeInfoEntity.id,
                place_name = officeInfoEntity.place_name,
                phone = officeInfoEntity.phone,
                address_name = officeInfoEntity.address_name,
                road_address_name = officeInfoEntity.road_address_name,
                x = officeInfoEntity.x,
                y = officeInfoEntity.y,
                place_url = officeInfoEntity.place_url,
                distance = officeInfoEntity.distance,
            )
        }
    }
}
