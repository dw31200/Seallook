package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.Profile

interface SignUpRepository {
    suspend fun signUp(profile: Profile, password: String? = null): Exception?
}
