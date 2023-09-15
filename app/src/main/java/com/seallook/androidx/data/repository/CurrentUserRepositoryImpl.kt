package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.auth.CurrentUserApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentUserRepositoryImpl @Inject constructor(
    private val currentUserApiService: CurrentUserApiService,
) : CurrentUserRepository {
    override fun getCurrentUser(): Flow<FirebaseUser?> {
        return flow {
            emit(currentUserApiService.getCurrentUser())
        }
    }
}
