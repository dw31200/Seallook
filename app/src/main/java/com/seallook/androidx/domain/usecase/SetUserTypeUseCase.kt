package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.auth.UserTypeRepository
import com.seallook.androidx.domain.model.UserTypeModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetUserTypeUseCase @Inject constructor(
    private val userTypeRepository: UserTypeRepository,
) {
    suspend operator fun invoke(user: FirebaseUser?, userType: UserTypeModel): Boolean? {
        return userTypeRepository.setItem(user, userType.toDataModel())
    }
}
