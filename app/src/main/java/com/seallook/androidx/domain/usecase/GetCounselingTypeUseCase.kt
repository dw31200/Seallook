package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.GetCounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselingTypeUseCase @Inject constructor(
    private val getCounselingTypeRepository: GetCounselingTypeRepository,
) {
    operator fun invoke(): Flow<List<CounselingTypeModel>> {
        return getCounselingTypeRepository.getCounselingType().map {
            it.map {
                CounselingTypeModel(it)
            }
        }
    }
}
