package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.UserTypeResponse
import kotlinx.coroutines.flow.Flow

interface GetUserTypeApiService {
    fun getUserType(user: FirebaseUser?): Flow<UserTypeResponse?>
}
