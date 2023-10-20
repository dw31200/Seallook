package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.OfficeInfo

interface OfficeInfoRepository {
    suspend fun getList(type: String, query: String): List<OfficeInfo>

    suspend fun setItem(info: OfficeInfo)

    suspend fun getItem(id: Int): OfficeInfo?

    suspend fun updateItem(info: OfficeInfo)
}
