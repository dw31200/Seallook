package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.CounselingType
import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.model.UserType

interface FirebaseFirestoreRepository {
    suspend fun getProfile(user: FirebaseUser?): Profile?

    suspend fun getUserType(user: FirebaseUser?): UserType?

    suspend fun setProfile(user: FirebaseUser?, profile: Profile)

    suspend fun setUserType(user: FirebaseUser?, type: UserType)

    suspend fun getCounselorInfo(user: FirebaseUser?): CounselorInfo?

    suspend fun setCounselorInfo(user: FirebaseUser?, info: CounselorInfo)

    suspend fun getCounselingType(user: FirebaseUser?): List<CounselingType>?

    suspend fun updateCounselingType(user: FirebaseUser?, type: List<CounselingType>)
}
