package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(params: Params): Result<AuthResult> {
        if (params.email == null || params.password == null) return Result.failure(Error("email or password is null"))
        return runCatching {
            firebaseAuthRepository.signInWithEmailAndPassword(params.email, params.password)
        }.map {
            if (it == null) throw Error("auth result is null")
            it
        }
    }

    data class Params(
        val email: String?,
        val password: String?,
    )
}
