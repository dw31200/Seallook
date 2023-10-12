package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.remote.FirebaseFirestoreApiService
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import javax.inject.Inject

class CounselorInfoRepositoryImpl @Inject constructor(
    private val firebaseFirestoreApiService: FirebaseFirestoreApiService,
    private val firebaseAuthApiService: FirebaseAuthApiService,
) : CounselorInfoRepository {
    override suspend fun getCounselorInfo(): CounselorInfo? {
        return firebaseFirestoreApiService.getCounselorInfo(firebaseAuthApiService.getCurrentUser())?.let { CounselorInfo(it) }
    }

    override suspend fun setCounselorInfo(info: CounselorInfo): Boolean? {
        return firebaseFirestoreApiService.setCounselorInfo(firebaseAuthApiService.getCurrentUser(), info.toResponse())
    }
}
