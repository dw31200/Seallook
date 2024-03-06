package com.seallook.androidx.data.model

import android.location.Location
import com.seallook.androidx.data.remote.model.FusedLocationResponse

data class FusedLocation(
    val location: Location?,
) {
    fun toRemoteModel(): FusedLocationResponse {
        return FusedLocationResponse(
            location = location,
        )
    }

    companion object {
        operator fun invoke(fusedLocationResponse: FusedLocationResponse): FusedLocation {
            return FusedLocation(
                location = fusedLocationResponse.location,
            )
        }
    }
}
