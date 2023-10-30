package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import com.seallook.androidx.domain.model.ProfileModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignUpUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(profile: ProfileModel, password: String): Result<AuthResult> {
        return runCatching {
            firebaseAuthRepository.signUp(profile.toDataModel(), password)
        }.map {
            if (it == null) throw Error("signup result is null")
            it
        }
    }
}
