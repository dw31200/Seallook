package com.seallook.androidx.domain.model

import android.location.Location
import com.seallook.androidx.data.model.UserLocation

data class UserLocationModel(
    val location: Location?,
) {
    fun toDataModel(): UserLocation {
        return UserLocation(
            location = location,
        )
    }

    companion object {
        operator fun invoke(userLocation: UserLocation): UserLocationModel {
            return UserLocationModel(
                location = userLocation.location,
            )
        }
    }
}
