package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.data.repository.SignUpRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    operator fun invoke(profile: ProfileEntity, password: String? = null): Flow<AuthResult?> {
        return flow {
            emit(signUpRepository.signUp(profile.toProfile(), password))
        }
    }

    suspend fun signUp(profile: ProfileEntity, password: String?): AuthResult? {
        return firebaseAuthRepository.signUp(profile.toProfile(), password)
    }
}
