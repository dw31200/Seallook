package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(): List<CounselingTypeModel> {
        return counselingTypeRepository.getCounselingType().map {
            CounselingTypeModel(it)
        }
    }
}
