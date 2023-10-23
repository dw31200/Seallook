package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(uid: String, type: List<CounselingTypeModel>): Boolean {
        return runCatching {
            counselingTypeRepository.updateList(uid, type.map { it.toDataModel() })
        }.fold(
            onSuccess = { true },
            onFailure = { false },
        )
    }
}
