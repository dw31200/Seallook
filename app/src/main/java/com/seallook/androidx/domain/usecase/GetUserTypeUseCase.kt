package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.GetUserTypeRepository
import com.seallook.androidx.domain.model.UserTypeEntity
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetUserTypeUseCase @Inject constructor(
    private val getUserTypeRepository: GetUserTypeRepository,
) {
    operator fun invoke(user: FirebaseUser?): Flow<UserTypeEntity> {
        return getUserTypeRepository.getUserType(user).map {
            it?.let { UserTypeEntity(it) } ?: UserTypeEntity(0)
        }
    }
}
