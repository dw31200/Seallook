package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.SignUpApiService
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpApiService: SignUpApiService,
) : SignUpRepository {
    override suspend fun signUp(profile: Profile, password: String?): Exception? {
        return signUpApiService.signUp(profile.toResponse(), password)
    }
}