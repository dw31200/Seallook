package com.seallook.androidx.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : SignInApiService {
    override suspend fun signInWithGoogle(token: String): Exception? {
        return withContext(Dispatchers.IO) {
            try {
                val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(firebaseCredential).await()
                return@withContext null
            } catch (e: Exception) {
                return@withContext e
            }
        }
    }
}