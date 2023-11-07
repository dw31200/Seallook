package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(email: String, type: List<CounselingTypeModel>): Result<Unit> {
        return runCatching {
            counselingTypeRepository.updateList(email, type.map { it.toDataModel() })
        }
    }
}
