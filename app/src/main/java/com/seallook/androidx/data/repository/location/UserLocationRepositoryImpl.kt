package com.seallook.androidx.data.repository.location

import com.seallook.androidx.data.device.service.UserLocationService
import com.seallook.androidx.data.model.UserLocation
import javax.inject.Inject

class UserLocationRepositoryImpl @Inject constructor(
    private val userLocationService: UserLocationService,
) : UserLocationRepository {
    override suspend fun getCurrentLocation(): Result<UserLocation> {
        return userLocationService.getCurrentLocation().map {
            UserLocation(it)
        }
    }
}
