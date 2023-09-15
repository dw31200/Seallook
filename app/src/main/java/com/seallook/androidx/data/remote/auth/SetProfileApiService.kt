package com.seallook.androidx.data.remote.auth

import com.seallook.androidx.data.remote.model.ProfileResponse

interface SetProfileApiService {
    suspend fun setProfile(profile: ProfileResponse)
}
