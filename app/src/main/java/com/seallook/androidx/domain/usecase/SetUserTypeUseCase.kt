package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.SetUserTypeRepository
import com.seallook.androidx.domain.model.UserTypeEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetUserTypeUseCase @Inject constructor(
    private val setUserTypeRepository: SetUserTypeRepository,
) {
    suspend operator fun invoke(user: UserTypeEntity) {
        setUserTypeRepository.setUserType(user.toUserType())
    }
}
