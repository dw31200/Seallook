package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.SignOutRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignOutUseCase @Inject constructor(
    private val signOutRepository: SignOutRepository,
) {
    operator fun invoke() {
        signOutRepository.signOut()
    }
}
