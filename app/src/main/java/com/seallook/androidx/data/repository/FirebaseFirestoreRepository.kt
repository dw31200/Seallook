package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.model.UserType

interface FirebaseFirestoreRepository {
    suspend fun getProfile(user: FirebaseUser?): Profile?

    suspend fun getUserType(user: FirebaseUser?): UserType?
    suspend fun setProfile(user: FirebaseUser?, profile: Profile)

    suspend fun setUserType(user: FirebaseUser?, type: UserType)
}
