package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.model.CounselorInfo
import kotlinx.coroutines.flow.Flow

interface CounselorInfoRepository {
    suspend fun getItem(uid: String): CounselorInfo?

    fun getAll(): Flow<List<CounselorInfo>>

    suspend fun setItem(uid: String, info: CounselorInfo)

    suspend fun refresh()
}
