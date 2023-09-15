package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.SignUpRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
) {
    suspend operator fun invoke(profile: ProfileEntity, password: String? = null): Exception? {
        return signUpRepository.signUp(profile.toProfile(), password)
    }
}