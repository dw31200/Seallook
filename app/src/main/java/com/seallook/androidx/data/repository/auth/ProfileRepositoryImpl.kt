package com.seallook.androidx.data.repository.auth

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.ProfileApiService
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiService: ProfileApiService,
) : ProfileRepository {
    override suspend fun getItem(uid: String): Profile? {
        return profileApiService.getItem(uid)?.let { Profile(it) }
    }

    override suspend fun setItem(uid: String, profile: Profile) {
        return profileApiService.setItem(uid, profile.toRemoteModel())
    }
}
