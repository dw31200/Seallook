package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.CounselorInfo

interface CounselorInfoRepository {
    suspend fun getCounselorInfo(): CounselorInfo?

    suspend fun setCounselorInfo(info: CounselorInfo)
}
