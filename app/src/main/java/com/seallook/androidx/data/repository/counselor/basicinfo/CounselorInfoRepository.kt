package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.model.CounselorInfo

interface CounselorInfoRepository {
    suspend fun getItem(): CounselorInfo?

    suspend fun setItem(info: CounselorInfo): Boolean?
}
