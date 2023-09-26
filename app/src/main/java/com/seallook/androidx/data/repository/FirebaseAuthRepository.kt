package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.ProfileResponse

interface FirebaseAuthRepository {
    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun signInWithGoogle(
        token: String,
    ): AuthResult?

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthResult?

    suspend fun signOut()

    suspend fun signUp(profile: ProfileResponse, password: String?): AuthResult?
}
