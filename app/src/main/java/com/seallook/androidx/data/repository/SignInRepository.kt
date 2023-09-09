package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
    fun getProfile(): Flow<Profile?>
    suspend fun signInWithGoogle(
        token: String,
    ): Exception?
}
