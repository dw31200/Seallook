package com.seallook.androidx.data.model

import android.location.Location
import com.seallook.androidx.data.device.model.UserLocationDeviceModel

data class UserLocation(
    val location: Location?,
) {
    fun toRemoteModel(): UserLocationDeviceModel {
        return UserLocationDeviceModel(
            location = location,
        )
    }

    companion object {
        operator fun invoke(userLocationDeviceModel: UserLocationDeviceModel): UserLocation {
            return UserLocation(
                location = userLocationDeviceModel.location,
            )
        }
    }
}
