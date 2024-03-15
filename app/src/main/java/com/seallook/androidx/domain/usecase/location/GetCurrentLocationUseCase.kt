package com.seallook.androidx.domain.usecase.location

import com.seallook.androidx.data.repository.location.UserLocationRepository
import com.seallook.androidx.domain.model.UserLocationModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentLocationUseCase @Inject constructor(
    private val userLocationRepository: UserLocationRepository,
) {
    suspend operator fun invoke(): Result<UserLocationModel> {
        return userLocationRepository.getCurrentLocation().map {
            UserLocationModel(it)
        }
    }
}
