package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CurrentUserApiService {
    fun getCurrentUser(): Flow<FirebaseUser?>
}