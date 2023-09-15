package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.GetProfileApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileRepositoryImpl @Inject constructor(
    private val getProfileApiService: GetProfileApiService,
) : GetProfileRepository {
    override fun getProfile(): Flow<Profile?> {
        return getProfileApiService.getProfile().map {
            it?.let {
                Profile(it)
            }
        }
    }
}
