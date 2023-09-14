package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.model.Profile
import kotlinx.coroutines.flow.StateFlow

interface SignInRepository {
    fun getProfile(): StateFlow<Profile?>

    suspend fun cacheProfile(profile: Profile)

    suspend fun signInWithGoogle(
        token: String,
    ): AuthResult?
}
