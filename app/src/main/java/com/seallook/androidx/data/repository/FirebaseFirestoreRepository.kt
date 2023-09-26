package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.data.remote.model.UserTypeResponse

interface FirebaseFirestoreRepository {
    suspend fun getProfile(user: FirebaseUser?): ProfileResponse?

    suspend fun getUserType(user: FirebaseUser?): UserTypeResponse?

    suspend fun setProfile(user: FirebaseUser?, profile: ProfileResponse)

    suspend fun setUserType(user: FirebaseUser?, type: UserTypeResponse)
}
