package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.CounselingTypeRepository
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselingTypeUseCase @Inject constructor(
    private val counselingTypeRepository: CounselingTypeRepository,
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): List<CounselingTypeModel>? {
        return firebaseFirestoreRepository.getCounselingType(firebaseAuthRepository.getCurrentUser())?.let {
            it.map {
                CounselingTypeModel(it)
            }
        }
        counselingTypeRepository.getCounselingType().map {
            CounselingTypeModel(it)
        }
    }
}
