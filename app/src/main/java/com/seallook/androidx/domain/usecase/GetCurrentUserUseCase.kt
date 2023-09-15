package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.CurrentUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val currentUserRepository: CurrentUserRepository,
) {
    operator fun invoke(): Flow<FirebaseUser?> {
        return currentUserRepository.getCurrentUser()
    }
}
