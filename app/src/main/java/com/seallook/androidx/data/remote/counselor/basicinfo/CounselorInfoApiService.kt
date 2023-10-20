package com.seallook.androidx.data.remote.counselor.basicinfo

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.CounselorInfoResponse

interface CounselorInfoApiService {
    suspend fun getItem(user: FirebaseUser?): CounselorInfoResponse?

    suspend fun setItem(user: FirebaseUser?, info: CounselorInfoResponse): Boolean?
}
