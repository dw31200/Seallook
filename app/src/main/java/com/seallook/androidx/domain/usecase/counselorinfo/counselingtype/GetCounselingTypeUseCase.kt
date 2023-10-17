package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(): List<CounselingTypeModel> {
        return counselingTypeRepository.getItemList().map {
            CounselingTypeModel(it)
        }
    }
}
