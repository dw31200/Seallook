package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.auth.ProfileRepository
import com.seallook.androidx.domain.model.ProfileModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetProfileWithEmailUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(params: Params): List<ProfileModel> {
        return profileRepository.getWithEmail(params.email).map {
            ProfileModel(it)
        }
    }

    data class Params(
        val _email: String?,
    ) {
        val email: String
            get() = _email ?: throw IllegalStateException("email is null")
    }
}
