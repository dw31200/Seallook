package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.SignInRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CacheProfileUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(profileEntity: ProfileEntity) {
        signInRepository.cacheProfile(profileEntity.toProfile())
    }
}
