package com.seallook.androidx.data.remote.counselor.counselingtype

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse

interface CounselingTypeApiService {
    suspend fun getList(user: FirebaseUser?): List<CounselingTypeResponse>

    suspend fun updateList(user: FirebaseUser?, type: CounselingTypeListResponse)
}
