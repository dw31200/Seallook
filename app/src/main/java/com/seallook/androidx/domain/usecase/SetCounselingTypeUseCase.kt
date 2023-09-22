package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.SetCounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetCounselingTypeUseCase @Inject constructor(
    private val setCounselingTypeRepository: SetCounselingTypeRepository,
) {
    suspend operator fun invoke(counselingTypeModel: CounselingTypeModel) {
        setCounselingTypeRepository.setCounselingType(counselingTypeModel.toType())
    }
}
