package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.repository.SetProfileRepository
import javax.inject.Inject

class SetProfileUseCase @Inject constructor(
    private val setProfileRepository: SetProfileRepository,
) {
    suspend operator fun invoke(profile: Profile) {
        setProfileRepository.setProfile(profile)
    }
}
