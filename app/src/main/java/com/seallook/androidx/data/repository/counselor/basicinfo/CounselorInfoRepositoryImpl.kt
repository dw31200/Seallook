package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import com.seallook.androidx.data.remote.counselor.basicinfo.CounselorInfoApiService
import javax.inject.Inject

class CounselorInfoRepositoryImpl @Inject constructor(
    private val counselorInfoApiService: CounselorInfoApiService,
    private val firebaseAuthApiService: FirebaseAuthApiService,
) : CounselorInfoRepository {
    override suspend fun getItem(): CounselorInfo? {
        return counselorInfoApiService.getItem(firebaseAuthApiService.getCurrentUser())?.let { CounselorInfo(it) }
    }

    override suspend fun setItem(info: CounselorInfo): Boolean? {
        return counselorInfoApiService.setItem(firebaseAuthApiService.getCurrentUser(), info.toRemoteModel())
    }
}
