package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult

interface SignInApiService {
    suspend fun signInWithGoogle(
        token: String,
    ): AuthResult?

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthResult?
}
