package com.seallook.androidx.data.repository

interface SignInRepository {
    suspend fun signInWithGoogle(
        token: String,
    ): Exception?
}