package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import javax.inject.Inject

class InitCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): List<CounselingTypeModel> {
        return counselingTypeRepository.initList(firebaseAuthRepository.getCurrentUser()).map {
            CounselingTypeModel(it)
        }
    }
}
