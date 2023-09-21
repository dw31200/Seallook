package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.remote.model.ProfileResponse

interface SignUpApiService {
    suspend fun signUp(profile: ProfileResponse, password: String?): AuthResult?
}
