package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.domain.model.UserTypeEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetUserTypeUseCase @Inject constructor(
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(user: FirebaseUser?): UserTypeEntity? {
        return firebaseFirestoreRepository.getUserType(user)?.let {
            UserTypeEntity(it)
        }
    }
}
