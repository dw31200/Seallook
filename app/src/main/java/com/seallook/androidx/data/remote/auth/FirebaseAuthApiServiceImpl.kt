package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : FirebaseAuthApiService {
    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override suspend fun signInWithGoogle(token: String): AuthResult? {
        val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
        return auth.signInWithCredential(firebaseCredential).await()
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult? {
        return auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun signUp(profile: ProfileResponse, password: String?): AuthResult? {
        return auth.createUserWithEmailAndPassword(profile.email, password!!).await()
    }
}
