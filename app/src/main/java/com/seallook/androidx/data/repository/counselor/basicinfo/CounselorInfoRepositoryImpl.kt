package com.seallook.androidx.data.repository.counselor.basicinfo

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.remote.counselor.basicinfo.CounselorInfoApiService
import javax.inject.Inject

class CounselorInfoRepositoryImpl @Inject constructor(
    private val counselorInfoApiService: CounselorInfoApiService,
    // private val firebaseAuthApiService: FirebaseAuthApiService,
) : CounselorInfoRepository {
    override suspend fun getItem(user: FirebaseUser): CounselorInfo? {
        return counselorInfoApiService
            .getItem(user)
            ?.let { CounselorInfo(it) }
    }

    override suspend fun setItem(user: FirebaseUser, info: CounselorInfo): Boolean? {
        return counselorInfoApiService
            .setItem(
                user,
                info.toResponse(),
            )
    }
}
