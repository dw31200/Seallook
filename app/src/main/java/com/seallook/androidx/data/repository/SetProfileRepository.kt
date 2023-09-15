package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile

interface SetProfileRepository {
    suspend fun setProfile(profile: Profile)
}
