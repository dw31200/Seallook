package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.data.repository.SignOutRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignOutUseCase @Inject constructor(
    private val signOutRepository: SignOutRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    operator fun invoke() {
        signOutRepository.signOut()
    }

    suspend fun signOut() {
        firebaseAuthRepository.signOut()
    }
}
