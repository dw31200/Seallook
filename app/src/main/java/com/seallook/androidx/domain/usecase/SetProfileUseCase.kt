package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.auth.ProfileRepository
import com.seallook.androidx.domain.model.ProfileModel
import javax.inject.Inject

class SetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(uid: String, profile: ProfileModel): Result<Unit> {
        return runCatching {
            profileRepository.setItem(uid, profile.toDataModel())
        }
    }
}
