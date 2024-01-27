package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.OfficeInfo
import kotlinx.coroutines.flow.Flow

interface OfficeInfoRepository {
    suspend fun setItem(info: OfficeInfo)

    suspend fun getAll(): List<OfficeInfo>

    fun getItem(id: String): Flow<OfficeInfo?>

    suspend fun updateItem(info: OfficeInfo)
}
