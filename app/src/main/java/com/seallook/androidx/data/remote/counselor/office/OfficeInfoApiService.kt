package com.seallook.androidx.data.remote.counselor.office

import com.seallook.androidx.data.remote.model.OfficeInfoResponse

interface OfficeInfoApiService {
    suspend fun getItem(id: String): OfficeInfoResponse?

    suspend fun updateItem(info: OfficeInfoResponse)
}
