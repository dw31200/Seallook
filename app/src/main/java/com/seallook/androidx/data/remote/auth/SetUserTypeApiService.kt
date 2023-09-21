package com.seallook.androidx.data.remote.auth

import com.seallook.androidx.data.remote.model.UserTypeResponse

interface SetUserTypeApiService {
    suspend fun setUserType(type: UserTypeResponse)
}
