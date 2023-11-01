package com.seallook.androidx.data.repository.counselor.list

import com.seallook.androidx.data.model.CounselorList

interface CounselorListRepository {
    suspend fun getAll(): List<CounselorList>
}
