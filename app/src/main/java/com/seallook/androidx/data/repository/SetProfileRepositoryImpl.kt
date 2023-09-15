package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.SetProfileApiService
import javax.inject.Inject

class SetProfileRepositoryImpl @Inject constructor(
    private val setProfileApiService: SetProfileApiService,
) : SetProfileRepository {
    override suspend fun setProfile(profile: Profile) {
        setProfileApiService.setProfile(profile.toResponse())
    }
}
