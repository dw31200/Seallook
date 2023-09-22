package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.remote.model.CounselingType
import com.seallook.androidx.data.repository.GetCounselingTypeRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetCounselingTypeUseCase @Inject constructor(
    private val getCounselingTypeRepository: GetCounselingTypeRepository,
) {
    operator fun invoke(): Flow<List<CounselingType>> {
        return getCounselingTypeRepository.getCounselingType()
    }
}
