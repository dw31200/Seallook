package com.seallook.androidx.data.remote

import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.flow.SharedFlow

interface SignInApiService {
    fun getProfile(): SharedFlow<ProfileResponse?>
    suspend fun signOut()
    suspend fun signInWithGoogle(
        token: String,
    ): Exception?
}
