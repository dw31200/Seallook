package com.seallook.androidx.data.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.ProfileApiService
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiService: ProfileApiService,
) : ProfileRepository {
    override suspend fun getItem(user: FirebaseUser?): Profile? {
        return profileApiService.getItem(user)?.let { Profile(it) }
    }

    override suspend fun setItem(user: FirebaseUser?, profile: Profile) {
        profileApiService.setItem(user, profile.toResponse())
    }
}
