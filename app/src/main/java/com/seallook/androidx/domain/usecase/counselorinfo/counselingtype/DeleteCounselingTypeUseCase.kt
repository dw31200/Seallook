package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.CounselingTypeRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class DeleteCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
) {
    suspend operator fun invoke(counselingTypeId: Int) {
        counselingTypeRepository.deleteCounselingType(counselingTypeId)
    }
}
