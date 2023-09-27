package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile

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

    suspend fun signUp(profile: Profile, password: String?): AuthResult?
}
