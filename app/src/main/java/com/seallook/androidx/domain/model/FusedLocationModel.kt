package com.seallook.androidx.domain.model

import android.location.Location
import com.seallook.androidx.data.model.FusedLocation

data class FusedLocationModel(
    val location: Location?,
) {
    fun toDataModel(): FusedLocation {
        return FusedLocation(
            location = location,
        )
    }

    companion object {
        operator fun invoke(fusedLocation: FusedLocation): FusedLocationModel {
            return FusedLocationModel(
                location = fusedLocation.location,
            )
        }
    }
}
