package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.GetProfileApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileRepositoryImpl @Inject constructor(
    private val getProfileApiService: GetProfileApiService,
) : GetProfileRepository {
    override fun getProfile(): Flow<Profile?> {
        return flow {
            val profile = getProfileApiService.getProfile()
            emit(profile?.let { Profile(it) })
        }
    }
}
