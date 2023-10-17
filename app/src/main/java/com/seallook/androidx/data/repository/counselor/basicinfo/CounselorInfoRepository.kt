package com.seallook.androidx.data.repository.counselor.basicinfo

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.CounselorInfo

interface CounselorInfoRepository {
    suspend fun getItem(user: FirebaseUser): CounselorInfo?

    suspend fun setItem(user: FirebaseUser, info: CounselorInfo): Boolean?
}
