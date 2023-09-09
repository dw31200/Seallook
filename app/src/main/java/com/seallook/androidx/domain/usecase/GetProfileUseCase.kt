package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.SignInRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetProfileUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    operator fun invoke(): Flow<ProfileEntity?> {
        return signInRepository.getProfile().map {
            it?.let { ProfileEntity(it) }
        }
    }
}