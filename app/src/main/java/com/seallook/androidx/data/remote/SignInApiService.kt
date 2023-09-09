package com.seallook.androidx.data.remote

import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface SignInApiService {
    fun getProfile(): Flow<ProfileResponse?>
    suspend fun signInWithGoogle(
        token: String,
    ): Exception?
}
