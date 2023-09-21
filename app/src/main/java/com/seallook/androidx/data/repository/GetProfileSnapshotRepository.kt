package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface GetProfileSnapshotRepository {
    fun getProfile(): Flow<Profile?>
}
