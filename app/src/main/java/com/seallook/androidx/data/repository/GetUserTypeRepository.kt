package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.UserType
import kotlinx.coroutines.flow.Flow

interface GetUserTypeRepository {
    fun getUserType(user: FirebaseUser?): Flow<UserType?>
}
