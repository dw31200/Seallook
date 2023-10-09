package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.CounselingTypeRepository
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(type: List<CounselingTypeModel>) {
        counselingTypeRepository.updateCounselingType(firebaseAuthRepository.getCurrentUser(), type.map { it.toType() })
    }
}
