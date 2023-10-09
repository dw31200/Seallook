package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.CounselingTypeRepository
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import javax.inject.Inject

class InitCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): List<CounselingTypeModel>? {
        return counselingTypeRepository.initCounselingType(firebaseAuthRepository.getCurrentUser())?.let {
            for (i in 0 until it.size) {
                counselingTypeRepository.setCounselingType(it[i])
            }
            it.map {
                CounselingTypeModel(it)
            }
        }
    }
}
