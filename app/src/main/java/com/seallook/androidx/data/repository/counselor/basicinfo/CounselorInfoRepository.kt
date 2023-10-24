package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.model.CounselorInfo

interface CounselorInfoRepository {
    suspend fun getItem(uid: String): CounselorInfo?

    suspend fun setItem(uid: String, info: CounselorInfo): Boolean
}