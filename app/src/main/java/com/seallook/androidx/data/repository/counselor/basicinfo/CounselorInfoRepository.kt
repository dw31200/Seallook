package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.model.CounselorInfo
import kotlinx.coroutines.flow.Flow

interface CounselorInfoRepository {
    suspend fun getItem(email: String): CounselorInfo?

    fun getAll(): Flow<List<CounselorInfo>>

    fun getList(query: String): Flow<List<CounselorInfo>>

    suspend fun setItem(info: CounselorInfo)

    suspend fun refresh()
}
