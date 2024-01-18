package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.OfficeInfo

interface OfficeInfoRepository {
    suspend fun setItem(info: OfficeInfo)

    suspend fun getAll(): List<OfficeInfo>

    suspend fun getItem(id: String): OfficeInfo?

    suspend fun updateItem(info: OfficeInfo)
}
