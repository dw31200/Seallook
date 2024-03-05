package com.seallook.androidx.data.repository.location

import com.seallook.androidx.data.model.FusedLocation
import com.seallook.androidx.data.remote.api.location.FusedLocationApiService
import javax.inject.Inject

class FusedLocationRepositoryImpl @Inject constructor(
    private val fusedLocationApiService: FusedLocationApiService,
) : FusedLocationRepository {
    override suspend fun getLastLocation(): FusedLocation {
        return FusedLocation(
            fusedLocationApiService.getLastLocation(),
        )
    }

    override suspend fun getCurrentLocation(): FusedLocation {
        return FusedLocation(
            fusedLocationApiService.getCurrentLocation(),
        )
    }
}
