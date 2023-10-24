package com.seallook.androidx.data.remote.counselor.basicinfo

import com.seallook.androidx.data.remote.model.CounselorInfoResponse

interface CounselorInfoApiService {
    suspend fun getItem(uid: String): CounselorInfoResponse?

    suspend fun setItem(uid: String, info: CounselorInfoResponse): Boolean
}
