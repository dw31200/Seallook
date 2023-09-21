package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : SignUpApiService {
    override suspend fun signUp(profile: ProfileResponse, password: String?): AuthResult? {
        return auth.createUserWithEmailAndPassword(profile.email, password!!).await()
    }
}
