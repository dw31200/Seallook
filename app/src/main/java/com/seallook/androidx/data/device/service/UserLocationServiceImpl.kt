package com.seallook.androidx.data.device.service

import android.Manifest
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.seallook.androidx.data.device.model.UserLocationDeviceModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserLocationServiceImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : UserLocationService {
    @RequiresPermission(
        anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
    )
    override suspend fun getCurrentLocation(): Result<UserLocationDeviceModel> {
        return runCatching {
            UserLocationDeviceModel(
                fusedLocationProviderClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token,
                ).await(),
            )
        }
    }
}
