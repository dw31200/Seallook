package com.seallook.androidx.domain.usecase.location

import com.seallook.androidx.data.repository.location.FusedLocationRepository
import com.seallook.androidx.domain.model.FusedLocationModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentLocationUseCase @Inject constructor(
    private val fusedLocationRepository: FusedLocationRepository,
) {
    suspend operator fun invoke(): FusedLocationModel {
        return FusedLocationModel(
            fusedLocationRepository.getCurrentLocation(),
        )
    }
}
