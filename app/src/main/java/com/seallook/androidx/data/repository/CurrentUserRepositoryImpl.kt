package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.auth.CurrentUserApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrentUserRepositoryImpl @Inject constructor(
    private val currentUserApiService: CurrentUserApiService,
) : CurrentUserRepository {
    override fun getCurrentUser(): Flow<FirebaseUser?> {
        return currentUserApiService.getCurrentUser()
    }
}