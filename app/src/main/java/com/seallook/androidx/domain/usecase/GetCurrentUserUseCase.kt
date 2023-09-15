package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.CurrentUserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val currentUserRepository: CurrentUserRepository,
) {
    operator fun invoke(): FirebaseUser? {
        return currentUserRepository.getCurrentUser()
    }
}
