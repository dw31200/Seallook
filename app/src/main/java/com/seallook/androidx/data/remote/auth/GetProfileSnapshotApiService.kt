package com.seallook.androidx.data.remote.auth

import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface GetProfileSnapshotApiService {
    fun getProfile(): Flow<ProfileResponse?>
}
