package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.auth.ProfileRepository
import com.seallook.androidx.domain.model.ProfileModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetUserTypeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(uid: String, userType: ProfileModel): Boolean? {
        return profileRepository.setItem(uid, userType.toDataModel())
    }
}
