package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.CounselingType
import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.model.UserType
import com.seallook.androidx.data.remote.FirebaseFirestoreApiService
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
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

    override suspend fun getCounselorInfo(user: FirebaseUser?): CounselorInfo? {
        return firebaseFirestoreApiService.getCounselorInfo(user)?.let { CounselorInfo(it) }
    }

    override suspend fun setCounselorInfo(user: FirebaseUser?, info: CounselorInfo) {
        firebaseFirestoreApiService.setCounselorInfo(user, info.toResponse())
    }

    override suspend fun getCounselingType(user: FirebaseUser?): List<CounselingType?> {
        return firebaseFirestoreApiService.getCounselingType(user).map {
            it?.let {
                CounselingType(it)
            }
        }
    }

    override suspend fun updateCounselingType(user: FirebaseUser?, type: List<CounselingType>) {
        firebaseFirestoreApiService.updateCounselingType(user, CounselingTypeListResponse(type.map { it.toResponse() }))
    }
}
