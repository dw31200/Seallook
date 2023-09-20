package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface GetProfileRepository {
    fun getProfile(): Flow<Profile>
}
