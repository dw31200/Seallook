package com.seallook.androidx.data.remote.counselor.office

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.OfficeInfoResponse

interface OfficeInfoApiService {
    suspend fun getList(type: String, query: String): List<OfficeInfoResponse>

    suspend fun getItem(user: FirebaseUser?): OfficeInfoResponse?

    suspend fun updateItem(user: FirebaseUser?, info: OfficeInfoResponse)
}
