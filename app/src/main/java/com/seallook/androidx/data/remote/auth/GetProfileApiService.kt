package com.seallook.androidx.data.remote.auth

import com.seallook.androidx.data.remote.model.ProfileResponse

interface GetProfileApiService {
    suspend fun getProfile(): ProfileResponse?
}
