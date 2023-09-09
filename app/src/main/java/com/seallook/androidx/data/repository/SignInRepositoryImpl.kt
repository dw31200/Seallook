package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.SignInApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService,
) : SignInRepository {
    override fun getProfile(): Flow<Profile?> {
        return signInApiService.getProfile().map {
            it?.let { Profile(it) }
        }
    }

    override suspend fun signInWithGoogle(token: String): Exception? {
        return signInApiService.signInWithGoogle(token)
    }
}
