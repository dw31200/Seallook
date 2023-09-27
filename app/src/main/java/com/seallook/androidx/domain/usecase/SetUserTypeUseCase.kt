package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.data.repository.SetUserTypeRepository
import com.seallook.androidx.domain.model.UserTypeEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetUserTypeUseCase @Inject constructor(
    private val setUserTypeRepository: SetUserTypeRepository,
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(user: UserTypeEntity) {
        setUserTypeRepository.setUserType(user.toUserType())
    }

    suspend fun setUserType(user: FirebaseUser?, userType: UserTypeEntity) {
        firebaseFirestoreRepository.setUserType(user, userType.toUserType())
    }
}
