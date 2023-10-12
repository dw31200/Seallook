package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.OfficeInfo

interface OfficeInfoRepository {
    suspend fun getOfficeInfo(id: Int): OfficeInfo?

    suspend fun setOfficeInfo(id: Int, info: OfficeInfo)

    suspend fun updateOfficeInfo(info: OfficeInfo)
}
