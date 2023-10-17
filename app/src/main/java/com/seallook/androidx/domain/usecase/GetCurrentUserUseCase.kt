package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): FirebaseUser? {
        return firebaseAuthRepository.getCurrentUser()
    }
}
