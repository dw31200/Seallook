package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.SignInRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignOutUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    operator fun invoke() {
        signInRepository.signOut()
    }
}