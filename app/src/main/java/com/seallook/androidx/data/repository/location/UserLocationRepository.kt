package com.seallook.androidx.data.repository.location

import com.seallook.androidx.data.model.UserLocation

interface UserLocationRepository {
    suspend fun getCurrentLocation(): Result<UserLocation>
}
