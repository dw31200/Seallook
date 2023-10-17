package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(type: List<CounselingTypeModel>): Boolean? {
        return runCatching {
            counselingTypeRepository.updateList(getCurrentUserUseCase(), type.map { it.toType() })
        }.fold(
            onSuccess = { true },
            onFailure = { false },
        )
    }
}
