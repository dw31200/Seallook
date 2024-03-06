package com.seallook.androidx.data.remote.api.location

import com.seallook.androidx.data.remote.model.FusedLocationResponse

interface FusedLocationApiService {
    suspend fun getLastLocation(): FusedLocationResponse

    suspend fun getCurrentLocation(): FusedLocationResponse
}
