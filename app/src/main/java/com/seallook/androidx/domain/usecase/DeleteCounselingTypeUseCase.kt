package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.DeleteCounselingTypeRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class DeleteCounselingTypeUseCase @Inject constructor(
    private val deleteCounselingTypeRepository: DeleteCounselingTypeRepository,
) {
    suspend operator fun invoke(counselingTypeId: Int) {
        deleteCounselingTypeRepository.deleteCounselingType(counselingTypeId)
    }
}
