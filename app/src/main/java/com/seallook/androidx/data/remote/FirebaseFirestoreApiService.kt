package com.seallook.androidx.data.remote

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.data.remote.model.CounselorInfoResponse
import com.seallook.androidx.data.remote.model.OfficeInfoResponse
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.data.remote.model.UserTypeResponse

interface FirebaseFirestoreApiService {
    suspend fun getProfile(user: FirebaseUser?): ProfileResponse?

    suspend fun getUserType(user: FirebaseUser?): UserTypeResponse?

    suspend fun setProfile(user: FirebaseUser?, profile: ProfileResponse)

    suspend fun setUserType(user: FirebaseUser?, type: UserTypeResponse)

    suspend fun getCounselorInfo(user: FirebaseUser?): CounselorInfoResponse?

    suspend fun setCounselorInfo(user: FirebaseUser?, info: CounselorInfoResponse): Boolean?

    suspend fun getCounselingType(user: FirebaseUser?): List<CounselingTypeResponse>?

    suspend fun updateCounselingType(user: FirebaseUser?, type: CounselingTypeListResponse)

    suspend fun getOfficeInfo(user: FirebaseUser?): OfficeInfoResponse?

    suspend fun setOfficeInfo(user: FirebaseUser?, info: OfficeInfoResponse)
}
