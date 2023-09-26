package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.auth.FirebaseFirestoreApiService
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.data.remote.model.UserTypeResponse
import javax.inject.Inject

class FirebaseFirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestoreApiService: FirebaseFirestoreApiService,
) : FirebaseFirestoreRepository {
    override suspend fun getProfile(user: FirebaseUser?): ProfileResponse? {
        return firebaseFirestoreApiService.getProfile(user)
    }

    override suspend fun getUserType(user: FirebaseUser?): UserTypeResponse? {
        return firebaseFirestoreApiService.getUserType(user)
    }

    override suspend fun setProfile(user: FirebaseUser?, profile: ProfileResponse) {
        firebaseFirestoreApiService.setProfile(user, profile)
    }

    override suspend fun setUserType(user: FirebaseUser?, type: UserTypeResponse) {
        firebaseFirestoreApiService.setUserType(user, type)
    }
}
