package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class DeleteCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(counselingTypeId: String) {
        counselingTypeRepository.deleteItem(counselingTypeId)
    }
}
