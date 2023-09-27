package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignUpUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(profile: ProfileEntity, password: String?): AuthResult? {
        return firebaseAuthRepository.signUp(profile.toProfile(), password)
    }
}
