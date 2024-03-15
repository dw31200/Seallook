package com.seallook.androidx.data.device.service

import com.seallook.androidx.data.device.model.UserLocationDeviceModel

interface UserLocationService {
    suspend fun getCurrentLocation(): Result<UserLocationDeviceModel>
}
