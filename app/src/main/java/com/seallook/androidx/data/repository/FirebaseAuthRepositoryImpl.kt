package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import com.seallook.androidx.data.remote.model.ProfileResponse
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuthApiService: FirebaseAuthApiService,
) : FirebaseAuthRepository {
    override suspend fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthApiService.getCurrentUser()
    }

    override suspend fun signInWithGoogle(token: String): AuthResult? {
        return firebaseAuthApiService.signInWithGoogle(token)
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult? {
        return firebaseAuthApiService.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signOut() {
        firebaseAuthApiService.signOut()
    }

    override suspend fun signUp(profile: ProfileResponse, password: String?): AuthResult? {
        return firebaseAuthApiService.signUp(profile, password)
    }
}
