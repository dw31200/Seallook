package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(counselingTypeModel: CounselingTypeModel) {
        counselingTypeRepository.insert(counselingTypeModel.toDataModel())
    }

    suspend operator fun invoke(counselingTypeListModel: List<CounselingTypeModel>) {
        counselingTypeRepository.insert(counselingTypeListModel.map { it.toDataModel() })
    }
}
