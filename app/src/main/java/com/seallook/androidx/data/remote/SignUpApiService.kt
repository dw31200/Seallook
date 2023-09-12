package com.seallook.androidx.data.remote

import com.seallook.androidx.data.remote.model.ProfileResponse

interface SignUpApiService {
    suspend fun signUp(profile: ProfileResponse, password: String?): Exception?
}

