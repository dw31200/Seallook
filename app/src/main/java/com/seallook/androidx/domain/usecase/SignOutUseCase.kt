package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignOutUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): Result<Unit> {
        return runCatching {
            firebaseAuthRepository.signOut()
        }
    }
}
