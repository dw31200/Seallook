package com.seallook.androidx.data.remote.auth

import com.seallook.androidx.data.remote.model.ProfileResponse

interface ProfileApiService {
    suspend fun getItem(uid: String): ProfileResponse?

    suspend fun getWithEmail(email: String): List<ProfileResponse>

    suspend fun setItem(uid: String, profile: ProfileResponse)
}
