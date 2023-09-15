package com.seallook.androidx.data.remote.auth

import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface GetProfileApiService {
    fun getProfile(): Flow<ProfileResponse?>
}
