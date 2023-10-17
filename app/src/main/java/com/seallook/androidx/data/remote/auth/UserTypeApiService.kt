package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.UserTypeResponse

interface UserTypeApiService {
    suspend fun getItem(user: FirebaseUser): UserTypeResponse?

    suspend fun setItem(user: FirebaseUser, type: UserTypeResponse): Boolean
}
