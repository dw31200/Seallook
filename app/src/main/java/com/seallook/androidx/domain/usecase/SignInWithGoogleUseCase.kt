package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.SignInRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignInWithGoogleUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(token: String): AuthResult? = signInRepository.signInWithGoogle(token)
}
