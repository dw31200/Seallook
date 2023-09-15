package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    fun getCurrentUser(): Flow<FirebaseUser?>
}