package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.model.Profile

interface SignUpRepository {
    suspend fun signUp(profile: Profile, password: String? = null): AuthResult?
}
