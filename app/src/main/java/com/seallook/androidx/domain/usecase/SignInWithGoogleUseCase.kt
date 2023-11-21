package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignInWithGoogleUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(token: String): Result<AuthResult> {
        return runCatching {
            firebaseAuthRepository.signInWithGoogle(token)
        }.map {
            if (it == null) throw Error("auth result is null")
            it
        }
    }
}
