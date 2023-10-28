package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
// import com.seallook.androidx.share.SeallookError
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(params: Params): Result<AuthResult> {
        val (email, password) = params
        if (email == null || password == null) return Result.failure(Error("email or password is null"))
        return runCatching {
            firebaseAuthRepository.signInWithEmailAndPassword(
                email,
                password,
            )
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
