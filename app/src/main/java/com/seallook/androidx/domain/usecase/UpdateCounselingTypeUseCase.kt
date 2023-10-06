package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.domain.model.CounselingTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateCounselingTypeUseCase @Inject constructor(
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(user: FirebaseUser?, type: List<CounselingTypeModel>) {
        firebaseFirestoreRepository.updateCounselingType(user, type.map { it.toType() })
    }
}
