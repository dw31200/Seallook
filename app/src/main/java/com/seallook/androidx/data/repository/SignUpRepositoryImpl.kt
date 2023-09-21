package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.SignUpApiService
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpApiService: SignUpApiService,
) : SignUpRepository {
    override suspend fun signUp(profile: Profile, password: String?): AuthResult? {
        return signUpApiService.signUp(profile.toResponse(), password)
    }
}
