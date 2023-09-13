package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.flow.SharedFlow

interface SignInApiService {
    fun getProfile(): SharedFlow<ProfileResponse?>
    fun signOut()
    suspend fun signInWithGoogle(
        token: String,
    ): AuthResult?
}
