package com.seallook.androidx.data.remote

interface SignInApiService {
    suspend fun signInWithGoogle(
        token: String,
    ): Exception?
}
