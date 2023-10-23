package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCounselingTypeRemoteUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(uid: String): List<CounselingTypeModel> {
        return counselingTypeRepository.getAll(uid).map {
            CounselingTypeModel(it)
        }
    }
}
