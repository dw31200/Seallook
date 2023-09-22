package com.seallook.androidx.data.repository

import com.seallook.androidx.data.remote.model.CounselingType
import kotlinx.coroutines.flow.Flow

interface GetCounselingTypeRepository {
    fun getCounselingType(): Flow<List<CounselingType>>
}
