package com.seallook.androidx.data.repository.auth

import com.seallook.androidx.data.model.Profile

interface ProfileRepository {
    suspend fun getItem(uid: String): Profile?

    suspend fun setItem(uid: String, profile: Profile)
}
