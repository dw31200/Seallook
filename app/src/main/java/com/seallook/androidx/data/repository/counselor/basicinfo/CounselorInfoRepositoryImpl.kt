package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.remote.counselor.basicinfo.CounselorInfoApiService
import javax.inject.Inject

class CounselorInfoRepositoryImpl @Inject constructor(
    private val counselorInfoApiService: CounselorInfoApiService,
) : CounselorInfoRepository {
    override suspend fun getItem(uid: String): CounselorInfo? {
        return counselorInfoApiService.getItem(uid)?.let { CounselorInfo(it) }
    }

    override suspend fun getAll(): List<CounselorInfo> {
        return counselorInfoApiService.getAll().map {
            CounselorInfo(it)
        }
    }

    override suspend fun setItem(uid: String, info: CounselorInfo) {
        counselorInfoApiService.setItem(uid, info.toRemoteModel())
    }
}
