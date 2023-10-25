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
    suspend operator fun invoke(profile: ProfileModel, password: String): AuthResult? {
        return firebaseAuthRepository.signUp(profile.toDataModel(), password)
    }
}
