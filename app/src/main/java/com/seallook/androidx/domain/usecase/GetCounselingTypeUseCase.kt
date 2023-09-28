package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    operator fun invoke(): Flow<List<CounselingTypeModel>> {
        return counselingTypeRepository.getCounselingType().map {
            it.map {
                CounselingTypeModel(it)
            }
        }
    }
}
