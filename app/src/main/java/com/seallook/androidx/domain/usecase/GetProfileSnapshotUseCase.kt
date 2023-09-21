package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.GetProfileSnapshotRepository
import com.seallook.androidx.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileSnapshotUseCase @Inject constructor(
    private val getProfileSnapshotRepository: GetProfileSnapshotRepository,
) {
    operator fun invoke(): Flow<ProfileEntity?> {
        return getProfileSnapshotRepository.getProfile().map {
            it?.let {
                ProfileEntity(it)
            }
        }
    }
}
