package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.CurrentUserRepository
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val currentUserRepository: CurrentUserRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    operator fun invoke(): Flow<FirebaseUser?> {
        return currentUserRepository.getCurrentUser()
    }
    suspend fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthRepository.getCurrentUser()
    }
}
