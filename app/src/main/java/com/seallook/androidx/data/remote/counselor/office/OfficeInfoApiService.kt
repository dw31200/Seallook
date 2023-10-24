package com.seallook.androidx.data.remote.counselor.office

import com.seallook.androidx.data.remote.model.OfficeInfoResponse

interface OfficeInfoApiService {
    suspend fun getList(type: String, query: String): List<OfficeInfoResponse>

    suspend fun getItem(uid: String): OfficeInfoResponse?

    suspend fun updateItem(uid: String, info: OfficeInfoResponse)
}
