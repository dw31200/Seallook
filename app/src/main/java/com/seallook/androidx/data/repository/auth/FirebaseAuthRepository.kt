package com.seallook.androidx.data.repository.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

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

    suspend fun signUp(email: String, password: String): AuthResult?
}
