package com.seallook.androidx.data.repository

import com.seallook.androidx.data.remote.SignInApiService
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService,
) : SignInRepository {
    override suspend fun signInWithGoogle(token: String): Exception? {
        return signInApiService.signInWithGoogle(token)
    }
}