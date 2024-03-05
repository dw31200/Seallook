package com.seallook.androidx.data.remote.api.location

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.seallook.androidx.data.remote.model.FusedLocationResponse
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FusedLocationApiServiceImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : FusedLocationApiService {
    override suspend fun getLastLocation(): FusedLocationResponse {
        return try {
            FusedLocationResponse(
                fusedLocationProviderClient.lastLocation.await(),
            )
        } catch (e: SecurityException) {
            Timber.e("Lost location permissions. " + e)
            FusedLocationResponse(null)
        }
    }

    override suspend fun getCurrentLocation(): FusedLocationResponse {
        return try {
            FusedLocationResponse(
                fusedLocationProviderClient.getCurrentLocation(
                    Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                    CancellationTokenSource().token,
                ).await(),
            )
        } catch (e: SecurityException) {
            Timber.e("Lost location permissions. " + e)
            FusedLocationResponse(null)
        }
    }
}
