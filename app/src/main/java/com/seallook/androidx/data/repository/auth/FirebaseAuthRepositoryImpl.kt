package com.seallook.androidx.data.repository.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
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

    override suspend fun signUp(profile: Profile, password: String): AuthResult? {
        return firebaseAuthApiService.signUp(profile.toRemoteModel(), password)
    }
}
