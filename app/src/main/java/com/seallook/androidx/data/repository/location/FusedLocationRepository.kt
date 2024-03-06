package com.seallook.androidx.data.repository.location

import com.seallook.androidx.data.model.FusedLocation

interface FusedLocationRepository {
    suspend fun getLastLocation(): FusedLocation

    suspend fun getCurrentLocation(): FusedLocation
}
