package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.auth.ProfileRepository
import com.seallook.androidx.domain.model.ProfileModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetUserTypeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(uid: String): ProfileModel? {
        return profileRepository.getItem(uid)?.let {
            ProfileModel(it)
        }
    }
}
