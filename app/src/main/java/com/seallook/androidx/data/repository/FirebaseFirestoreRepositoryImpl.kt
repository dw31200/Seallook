package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.model.UserType
import com.seallook.androidx.data.remote.auth.FirebaseFirestoreApiService
import javax.inject.Inject

class FirebaseFirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestoreApiService: FirebaseFirestoreApiService,
) : FirebaseFirestoreRepository {
    override suspend fun getProfile(user: FirebaseUser?): Profile? {
        return firebaseFirestoreApiService.getProfile(user)?.let { Profile(it) }
    }

    override suspend fun getUserType(user: FirebaseUser?): UserType? {
        return firebaseFirestoreApiService.getUserType(user)?.let { UserType(it) }
    }

    override suspend fun setProfile(user: FirebaseUser?, profile: Profile) {
        firebaseFirestoreApiService.setProfile(user, profile.toResponse())
    }

    override suspend fun setUserType(user: FirebaseUser?, type: UserType) {
        firebaseFirestoreApiService.setUserType(user, type.toResponse())
    }
}
