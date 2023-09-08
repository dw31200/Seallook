package com.seallook.androidx.data.remote

import com.seallook.androidx.data.remote.model.Profile

interface SignUpApiService {
    suspend fun signUp(profile: Profile, password: String?): Exception?
}

