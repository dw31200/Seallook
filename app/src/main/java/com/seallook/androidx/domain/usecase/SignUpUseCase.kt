package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignUpUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(params: Params): Result<AuthResult> {
        if (params.email == null || params.password == null) return Result.failure(Error("email or password is null"))
        return runCatching {
            firebaseAuthRepository.signUp(params.email, params.password)
        }.map {
            if (it == null) throw Error("signup result is null")
            it
        }
    }

    data class Params(
        val email: String?,
        val password: String?,
    )
}
